import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static long[] dist;
    static List<Edge> edges;

    static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N + 1];
        edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }

        StringBuilder sb = new StringBuilder();
        boolean hasNegativeCycle = bellmanFord();

        if (hasNegativeCycle) {
            System.out.println("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                // 경로가 없는 경우 (INF 그대로인 경우) -1 출력
                if (dist[i] == INF) {
                    sb.append("-1").append("\n");
                } else {
                    sb.append(dist[i]).append("\n");
                }
            }
            System.out.print(sb);
        }
    }

    static boolean bellmanFord() {
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i < N - 1; i++) {

            for (Edge edge : edges) {
                if (dist[edge.u] == INF) {
                    continue;
                }

                if (dist[edge.v] > dist[edge.u] + edge.w) {
                    dist[edge.v] = dist[edge.u] + edge.w;
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.u] != INF && dist[edge.v] > dist[edge.u] + edge.w) {
                return true;
            }
        }
        return false;
    }
}
