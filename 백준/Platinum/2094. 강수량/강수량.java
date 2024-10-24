
import java.io.BufferedReader;
import java.io.FileInputStream;
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
        // System.setIn(new FileInputStream("src/P2094/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // n개의 강수량 정보 입력
        N = Integer.parseInt(br.readLine());


        while (S < N) {
            S *= 2;
        }

        infos = new Info[N + 1];
        tree = new Info[2 * S];

        // 트리에 데이터 업데이트

        for (int i = 0; i < S; i++) {
            tree[S + i] = new Info(0, 0);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int year = Integer.parseInt(st.nextToken());
            int rain = Integer.parseInt(st.nextToken());

            Info info = new Info(year, rain);
            infos[i] = info;
            tree[S + i - 1] = info;
            map.put(year, new IdxRain(i, rain));
        }

        init();

        // m개의 query
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int beforeY = Integer.parseInt(st.nextToken());
            int nowX = Integer.parseInt(st.nextToken());

            if (!map.containsKey(beforeY) && !map.containsKey(nowX)) {
                sb.append("maybe").append("\n");
            } else if (map.containsKey(beforeY) && !map.containsKey(nowX)) {
                int queryLeft = map.get(beforeY).index + 1;
                int queryRight = binarySearch(nowX) - 1;
                int maxRain = query(1, 1, S, queryLeft, queryRight);

                if (maxRain < map.get(beforeY).rain) {
                    sb.append("maybe").append("\n");
                } else {
                    sb.append("false").append("\n");
                }

            } else if (!map.containsKey(beforeY) && map.containsKey(nowX)) {
                int queryLeft = binarySearch(beforeY);
                int queryRight = map.get(nowX).index - 1;
                int maxRain = query(1, 1, S, queryLeft, queryRight);

                if (maxRain >= map.get(nowX).rain) {
                    sb.append("false").append("\n");
                } else {
                    sb.append("maybe").append("\n");
                }
            } else if ((map.containsKey(beforeY) && map.containsKey(nowX))) {
                int queryLeft = map.get(beforeY).index + 1;
                int queryRight = map.get(nowX).index - 1;
                int maxRain = query(1, 1, S, queryLeft, queryRight);

                if (map.get(beforeY).rain < map.get(nowX).rain || maxRain >= map.get(nowX).rain) {
                    sb.append("false").append("\n");
                } else if (queryRight - queryLeft == nowX - beforeY - 2) {
                    sb.append("true").append("\n");
                } else {
                    sb.append("maybe").append("\n");
                }
            }
        }

        System.out.print(sb);

    }

    static void init() {
        for (int i = S - 1; i >= 1; i--) {
            tree[i] = new Info(0, 0);
            tree[i].rain = Math.max(tree[i * 2].rain, tree[i * 2 + 1].rain);
        }
    }

    static int binarySearch(int year) {
        int left = 1;
        int right = N;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;
            int midYear = infos[mid].year;

            if (midYear < year) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static int query(int node, int start, int end, int queryLeft, int queryRight) {
        if (start > queryRight || end < queryLeft) {
            return 0;
        }

        if (start == end || (queryLeft <= start && end <= queryRight)) {
            return tree[node].rain;
        }

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