import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }

            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            weight = new int[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (type.equals("!")) {
                    int w = Integer.parseInt(st.nextToken());
                    union(a, b, w);
                } else if (type.equals("?")) {
                    if (find(a) == find(b)) {
                        sb.append(weight[b] - weight[a]).append("\n");
                    } else {
                        sb.append("UNKNOWN").append("\n");
                    }
                }
            }
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        int p = find(parent[x]);
        weight[x] += weight[parent[x]];
        return parent[x] = p;
    }

    static void union(int x, int y, int w) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot != yRoot) {
            parent[yRoot] = xRoot;
            weight[yRoot] = weight[x] + w - weight[y];
        }
    }
}
