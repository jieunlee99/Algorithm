import java.io.*;
import java.util.*;

/**
 * [문제 컨셉 요약]
 * - 선수 i의 "최종 등수" = 자기 앞에 있으면서 능력치가 자신보다 낮은(=추월 가능한) 선수 수만큼 올라감
 * - 여기 구현은 "능력치(stat) 오름차순"으로 선수를 처리하면서,
 *   이미 처리된(=자신보다 능력치 낮은) 선수들의 "원래 등수(rank)" 위치에 1을 찍어두고
 *   현재 선수의 원래 등수보다 앞쪽[1..rank-1]에 찍힌 1의 개수를 세서
 *   result[rank] = rank - (앞쪽의 낮은 능력치 수) 로 계산한다.
 *
 * [세그먼트 트리 용도]
 * - 구간 [L..R] 내에 "표시된 선수 수(=합)"를 O(logN) 으로 질의(query)
 * - 특정 위치에 선수 등장 여부(0→1)를 O(logN) 으로 갱신(update)
 */
public class Main {

    static int N, S;
    static Player[] players;

    /**
     * 세그먼트 트리 저장 배열
     * - tree[node] = 이 노드가 담당하는 구간 [left..right] 내에 "표시된(=이미 처리된)" 선수 수의 합
     * - 크기: 2 * S (S는 N 이상의 가장 작은 2의 거듭제곱)
     *   왜? 완전이진트리(루트=1, 자식=2*node, 2*node+1) 형태로 인덱싱하기 쉬우며,
     *   리프(S개) + 내부노드(S-1개) ≈ 2*S 크기면 여유 있게 담긴다.
     */
    static int[] tree;

    /**
     * result[rank] = 최종 등수
     * - rank(=입력 순서, 1-indexed)를 그대로 인덱스로 사용하기 위해 N+1 크기 할당
     */
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // S = N 이상이 되는 최소 2의 거듭제곱
        // 세그먼트 트리를 완전이진트리로 만들기 위함
        S = 1;
        while (S < N) {
            S *= 2;
        }

        // ⚠️ 정확한 크기 설명: 2 * S (2*N-1 아님). S는 N보다 크거나 같은 2의 거듭제곱.
        tree = new int[2 * S];

        players = new Player[N];
        result = new int[N + 1];

        // players[i] = (원래 등수 = i+1, 능력치 = 입력값)
        for (int i = 0; i < N; i++) {
            players[i] = new Player(i + 1, Integer.parseInt(br.readLine()));
        }

        // 능력치(stat) 오름차순으로 정렬
        // Arrays.sort(Object[])는 "안정 정렬"이라 stat 동률이면 입력 순서(=rank 오름차순) 유지
        Arrays.sort(players, Comparator.comparing(Player::getStat));

        /**
         * 핵심 루프:
         * - 현재 선수(current)의 원래 등수 = current.rank
         * - 이미 세그트리에 표시된 선수들은 "능력치가 더 낮은" 선수들
         * - 따라서 [1 .. current.rank-1] 범위에 찍힌 1의 개수 = 앞에 있는 더 약한 선수 수
         *   -> 최종 등수 = current.rank - 그 개수
         * - 그런 다음 현재 선수 자리를 세그트리에 1로 업데이트
         *
         * 시간복잡도: 매 선수마다 query + update 각 O(logN) → 전체 O(N logN)
         */
        for (int i = 0; i < N; i++) {
            Player current = players[i];

            // [1 .. current.rank-1] 범위의 합 = 앞쪽에 있는 '더 약한' 선수 수
            int weakerInFront = query(1, S, 1, 1, current.rank - 1);

            // 최종 등수 = 원래 등수 - 앞에 있는 약한 선수 수
            result[current.rank] = current.rank - weakerInFront;

            // 현재 선수의 '원래 등수 위치'에 표시(=1 추가)
            update(1, S, 1, current.rank, 1);
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

    /**
     * [update]
     * - 의미: 단일 위치(target)에 diff(여기서는 +1)를 더한다. (점 갱신)
     *
     * 파라미터 설명
     * @param left       현재 노드가 담당하는 구간의 왼쪽 경계 (트리 내부 상태)
     * @param right      현재 노드가 담당하는 구간의 오른쪽 경계 (트리 내부 상태)
     * @param node       현재 노드 인덱스 (루트=1, 왼=2*node, 오=2*node+1)
     * @param target     갱신할 실제 위치(=원래 등수 rank)
     * @param diff       더할 값(여기서는 1; 선수 등장 표시)
     *
     * 동작
     * - target이 현재 구간 [left..right] 밖이면 return
     * - 안에 있으면 tree[node]에 diff 더함
     * - leaf가 아니면 왼쪽/오른쪽 자식으로 재귀 내려가서 계속 반영
     */
    static void update(int left, int right, int node, int target, long diff) {

        // [left..right] 바깥이면 갱신할 필요 없음
        if (target < left || right < target) {
            return;
        }

        // 이 노드가 담당하는 구간 내에 target이 포함 → 합에 diff 반영
        tree[node] += diff;

        // 내부 노드라면 더 내려가서 리프까지 반영
        if (left != right) {
            int mid = (left + right) / 2;
            update(left,  mid,     node * 2,     target, diff);     // 왼쪽 자식
            update(mid+1, right,   node * 2 + 1, target, diff);     // 오른쪽 자식
        }
    }

    /**
     * [query]
     * - 의미: 질의 구간 [queryLeft .. queryRight]의 합을 반환
     *
     * 파라미터 설명
     * @param left        현재 노드가 담당하는 구간의 왼쪽 경계 (트리 내부 상태)
     * @param right       현재 노드가 담당하는 구간의 오른쪽 경계 (트리 내부 상태)
     * @param node        현재 노드 인덱스
     * @param queryLeft   "사용자가 알고 싶은" 질의 구간의 왼쪽 경계
     * @param queryRight  "사용자가 알고 싶은" 질의 구간의 오른쪽 경계
     *
     * 핵심 구분
     * - left/right : "지금 이 노드가 대표하는 트리의 구간"
     * - queryLeft/queryRight : "내가 합을 구하고 싶은 실제 구간"
     *
     * 동작
     * - 완전히 벗어남 → 0
     * - 완전히 포함됨 → 현재 노드 값(tree[node])
     * - 일부만 겹침 → 자식 둘로 쪼개서 합산
     */
    static int query(int left, int right, int node, int queryLeft, int queryRight) {

        // 1) 전혀 겹치지 않으면 0
        if (queryRight < left || right < queryLeft) {
            return 0;
        }

        // 2) 현재 노드 구간이 질의 구간에 완전히 포함되면 이 노드 값 사용
        //    (주의: 조건은 queryLeft <= left && right <= queryRight)
        else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        // 3) 일부만 겹치면 자식으로 내려가서 합치기
        int mid = (left + right) / 2;
        return query(left,   mid,   node * 2,     queryLeft, queryRight)
             + query(mid+1, right,  node * 2 + 1, queryLeft, queryRight);
    }
}

/**
 * Player: 입력 순서(=원래 등수 rank)와 능력치 stat
 * - rank: 1-indexed (입력 i번째 → rank=i+1)
 * - stat: 능력치(작을수록 약함)
 */
class Player {
    int rank, stat;

    public Player(int rank, int stat) {
        this.rank = rank;
        this.stat = stat;
    }

    public int getRank() { return rank; }
    public int getStat() { return stat; }

    @Override
    public String toString() {
        return "Player [rank=" + rank + ", stat=" + stat + "]";
    }
}
