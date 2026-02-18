import java.io.*;
import java.util.*;

public class Main {

    static int TC, N, M, W, S, E, T;
    static int[] dist;
    static List<Edge> edges;

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 지점 수
            M = Integer.parseInt(st.nextToken()); // 도로 개수
            W = Integer.parseInt(st.nextToken()); // 웜홀 개수

            dist = new int[N + 1];
            edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken()); // 시작 지점
                E = Integer.parseInt(st.nextToken()); // 도착 지점
                T = Integer.parseInt(st.nextToken()); // 시간
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken()); // 시작 지점
                E = Integer.parseInt(st.nextToken()); // 도착 지점
                T = Integer.parseInt(st.nextToken()); // 시간
                edges.add(new Edge(S, E, T * (-1)));
            }

            sb.append(bellmanFord() ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }

    static boolean bellmanFord() {
        for (int i = 1; i < N; i++) {
            for (Edge edge : edges) {
                if (dist[edge.to] > dist[edge.from] + edge.cost) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        for(Edge edge:edges) {
            if(dist[edge.to] > dist[edge.from]+edge.cost) {
                return true; // 음수 사이클 발견
            }
        }

        return false; // 음수 사이클 없음
    }
}
