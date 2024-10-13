import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int S;
    static long[] nums;
    static long[] tree;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P2042/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[S * 2];
        initBU();

        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c =Long.parseLong(st.nextToken());

            if(a == 1) {
                updateTD(1, S, 1, b, c);
            } else if(a==2) {
                long sum = queryTD(1, S, 1, b, c);
                System.out.println(sum);
            }
        }

    }

    static void initBU() {
        // tree[0]은 비우고 1 ~ 2*S-1 을 채운다.

        // 리프 노드를 먼저 채운다.
        for (int i = 0; i < N; i++) {
            tree[S + i] = nums[i];
        }

        // 아래에서 위로 채운다.
        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static void updateTD(int left, int right, int node, int target, long newValue) {
        // target이 범위 밖에 있으면 return
        if (target < left || target > right) {
            return;
        }

        // target 위치의 값을 새로 넣을 값(newValue)로 교체해야 하므로,
        // diff를 계산 (새 값 - 기존 값)
        long diff = newValue - tree[target + S - 1];  // target을 1-based로 맞춰야 함

        // target 위치의 값을 diff만큼 더해주는 방식으로 트리 전체 갱신
        tree[node] += diff;

        // 리프 노드가 아니라면 자식으로 내려가서 업데이트 수행
        if (left != right) {
            int mid = (left + right) / 2;
            updateTD(left, mid, node * 2, target, newValue);
            updateTD(mid + 1, right, node * 2 + 1, target, newValue);
        }
    }


    static long queryTD(int left, int right, int node, int queryLeft, long queryRight) {
        if(queryRight < left || right < queryLeft) {
            return 0;
        } else if(queryLeft <= left && right <= queryRight) {
            return tree[node];
        } else {
            int mid = (left+right)/2;
            return queryTD(left, mid, node*2, queryLeft, queryRight) + queryTD(mid+1, right, node*2+1, queryLeft, queryRight);
        }
    }
}
