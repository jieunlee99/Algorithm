
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int S = 1;
    static Info[] infos;
    static Info[] tree;
    static HashMap<Integer, IdxRain> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 강수량 정보의 개수 입력 (N)
        N = Integer.parseInt(br.readLine());

        // 세그먼트 트리 크기를 N보다 큰 2의 제곱수로 조정
        while (S < N) {
            S *= 2;
        }

        // infos: 입력받은 강수량 정보를 저장할 배열 (1-based index)
        infos = new Info[N + 1];
        // tree: 세그먼트 트리 배열 (2 * S 크기로 초기화)
        tree = new Info[2 * S];

        // 초기화할 리프 노드 범위
        for (int i = S; i < 2 * S; i++) {
            tree[i] = new Info(0, 0);
        }

        // 강수량 정보 입력 및 세그먼트 트리 리프 노드에 저장
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int year = Integer.parseInt(st.nextToken());
            int rain = Integer.parseInt(st.nextToken());

            // info 객체 생성 후 infos 배열과 세그먼트 트리 리프 노드에 저장
            Info info = new Info(year, rain);
            infos[i] = info;
            tree[S + i - 1] = info;

            // map에 연도별 인덱스 및 강수량 저장
            map.put(year, new IdxRain(i, rain));
        }

        // 세그먼트 트리 초기화 (리프 노드 외의 노드들을 계산)
        init();

        // 쿼리의 개수 입력 (M)
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int beforeY = Integer.parseInt(st.nextToken());
            int nowX = Integer.parseInt(st.nextToken());

            IdxRain beforeYInfo = map.get(beforeY); // beforeY와 nowX가 있을 때 중복 호출 제거
            IdxRain nowXInfo = map.get(nowX);

            // 두 연도 모두 기록이 없는 경우: maybe 출력
            if (beforeYInfo == null && nowXInfo == null) {
                sb.append("maybe").append("\n");
            } else if (beforeYInfo != null && nowXInfo == null) {
                // 이전 연도는 기록이 있지만 현재 연도는 기록이 없는 경우
                int queryLeft = beforeYInfo.index + 1;
                int queryRight = binarySearch(nowX) - 1;
                int maxRain = query(1, 1, S, queryLeft, queryRight);

                if (maxRain < beforeYInfo.rain) {
                    sb.append("maybe").append("\n");
                } else {
                    sb.append("false").append("\n");
                }
            } else if (beforeYInfo == null && nowXInfo != null) {
                // 이전 연도는 기록이 없고 현재 연도는 기록이 있는 경우
                int queryLeft = binarySearch(beforeY);
                int queryRight = nowXInfo.index - 1;
                int maxRain = query(1, 1, S, queryLeft, queryRight);

                if (maxRain >= nowXInfo.rain) {
                    sb.append("false").append("\n");
                } else {
                    sb.append("maybe").append("\n");
                }
            } else {
                // 두 연도 모두 기록이 있는 경우
                int queryLeft = beforeYInfo.index + 1;
                int queryRight = nowXInfo.index - 1;
                int maxRain = query(1, 1, S, queryLeft, queryRight);

                if (beforeYInfo.rain < nowXInfo.rain || maxRain >= nowXInfo.rain) {
                    sb.append("false").append("\n");
                } else if (queryRight - queryLeft == nowX - beforeY - 2) {
                    sb.append("true").append("\n");
                } else {
                    sb.append("maybe").append("\n");
                }
            }
        }

        // 결과 출력
        System.out.print(sb);

    }

    // 세그먼트 트리 초기화 함수
    static void init() {
        // 리프 노드가 아닌 내부 노드들을 계산
        for (int i = S - 1; i >= 1; i--) {
            tree[i] = new Info(0, Math.max(tree[i * 2].rain, tree[i * 2 + 1].rain));
        }
    }

    // 이진 탐색을 통해 주어진 연도의 위치를 찾는 함수
    static int binarySearch(int year) {
        int left = 1;
        int right = N;

        // 연도를 기준으로 이진 탐색
        while (left < right) {
            int mid = (left + right) / 2;
            if (infos[mid].year < year) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 탐색 결과 반환
        return right;
    }

    // 세그먼트 트리를 사용하여 주어진 범위 내 최대 강수량을 구하는 함수
    static int query(int node, int start, int end, int queryLeft, int queryRight) {
        // 범위가 겹치지 않으면 0 반환
        if (start > queryRight || end < queryLeft) {
            return 0;
        }

        // 범위 내에 있으면 해당 노드의 값 반환
        if (queryLeft <= start && end <= queryRight) {
            return tree[node].rain;
        }

        // 구간을 반으로 나누어 좌우 자식 노드에서 최대값을 구함
        int mid = (start + end) / 2;
        return Math.max(query(node * 2, start, mid, queryLeft, queryRight), query(node * 2 + 1, mid + 1, end, queryLeft, queryRight));
    }
}

class Info {
    int year;
    int rain;

    public Info(int year, int rain) {
        this.year = year;
        this.rain = rain;
    }
}

class IdxRain {
    int index;
    int rain;

    public IdxRain(int index, int rain) {
        this.index = index;
        this.rain = rain;
    }
}
