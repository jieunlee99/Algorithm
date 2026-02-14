
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int V, E, K;
    static List<Edge>[] adjList;
    static boolean[] visited;
    static int[] weight;

    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 정점 개수
        E = Integer.parseInt(st.nextToken()); // 간선 개수

        visited = new boolean[V + 1];
        weight = new int[V + 1];
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        K = Integer.parseInt(br.readLine()); // 시작 정점 번호

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u].add(new Edge(v, w));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (weight[i] == INF) {
                sb.append("INF").append("\n");
            } else {
                sb.append(weight[i]).append("\n");
            }
        }
        System.out.print(sb);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        Arrays.fill(weight, INF);

        // K부터 시작
        weight[K] = 0;
        pq.offer(new Edge(K, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.to]) {
                continue;
            }

            visited[current.to] = true;

            for (Edge next : adjList[current.to]) {
                if (weight[next.to] > next.weight + current.weight) {
                    weight[next.to] = next.weight + current.weight;
                    pq.offer(new Edge(next.to, weight[next.to]));
                }
            }
        }
    }
}
