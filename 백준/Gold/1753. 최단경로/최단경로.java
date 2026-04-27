import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int V, E, K;
	static List<Edge>[] adjList;
	static boolean[] visited;
	static int[] dist;

	static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		K = Integer.parseInt(br.readLine());

		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		visited = new boolean[V + 1];
		dist = new int[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Edge(v, w));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.offer(new Edge(K, 0));
		dist[K] = 0;

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			if (visited[current.v]) {
				continue;
			}

			visited[current.v] = true;

			for (Edge next : adjList[current.v]) {
				if (dist[next.v] > next.w + current.w) {
					dist[next.v] = next.w + current.w;
					pq.offer(new Edge(next.v, dist[next.v]));
				}
			}
		}

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

}
