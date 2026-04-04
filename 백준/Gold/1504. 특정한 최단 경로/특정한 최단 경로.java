
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to, dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }
    }

    static List<Edge>[] adjList;
    static boolean[] visited;
    static int[] dist;
    static final int INF = 200_000 * 1_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        visited = new boolean[N + 1];
        dist = new int[N + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[a].add(new Edge(b, c));
            adjList[b].add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int cnt1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        int cnt2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        if (cnt1 >= INF && cnt2 >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(cnt1, cnt2));
        }
    }

    public static int dijkstra(int from, int to) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        Arrays.fill(visited, false);
        Arrays.fill(dist, INF);

        dist[from] = 0;
        pq.offer(new Edge(from, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.to]) {
                continue;
            }

            visited[current.to] = true;

            for (Edge next : adjList[current.to]) {
                if (dist[next.to] > dist[current.to] + next.dist) {
                    dist[next.to] = dist[current.to] + next.dist;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }

        return dist[to];
    }
}
