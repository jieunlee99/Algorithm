import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N, M, K, X;
    static int[] dist;
    static boolean[] visited;
    static List<Edge>[] adjList;

    static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return this.w - e.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시 개수
        M = Integer.parseInt(st.nextToken()); // 도로 개수
        K = Integer.parseInt(st.nextToken()); // 거리 정보
        X = Integer.parseInt(st.nextToken()); // 출발 번호

        dist = new int[N + 1];
        visited = new boolean[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(new Edge(v, 1));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                sb.append(i).append("\n");
            }
        }
        if (sb.length() == 0) {
            sb.append(-1).append("\n");
        }
        System.out.print(sb);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        Arrays.fill(dist, INF);
        dist[X] = 0;
        pq.offer(new Edge(X, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.v]) {
                continue;
            }

            visited[current.v] = true;

            for (Edge next : adjList[current.v]) {
                if (dist[next.v] > current.w + next.w) {
                    dist[next.v] = current.w + next.w;
                    pq.offer(new Edge(next.v, dist[next.v]));
                }
            }
        }
    }
}
