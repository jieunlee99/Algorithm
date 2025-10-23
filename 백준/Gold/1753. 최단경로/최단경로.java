import java.io.*;
import java.util.*;

public class Main {

	static int V, E; // 정점, 간선의 개수
	static int K; // 시작 정점

	static List<Edge>[] adjList;
	static boolean[] visited;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		visited = new boolean[V + 1];
		dist = new int[V + 1];

		K = Integer.parseInt(br.readLine());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Edge(v, w)); // 방향있는 간선
		}

		dijkstra();

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				bw.write("INF\n");
			} else {
				bw.write(dist[i] + "\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		pq.offer(new Edge(K, 0));

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			if (visited[current.to]) {
				continue;
			}

			visited[current.to] = true;

			for (Edge next : adjList[current.to]) {
				if (dist[next.to] > next.cost + current.cost) {
					dist[next.to] = next.cost + current.cost;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

	}
}