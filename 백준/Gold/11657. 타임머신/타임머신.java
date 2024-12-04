import java.util.*;
import java.io.*;

public class Main {

	static int N, M;

	static long[] dist;
	static ArrayList<Edge> edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dist = new long[N+1];
		edges = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edges.add(new Edge(a, b, c));
		}

		boolean hasNegativeCycle = bellmanFord();

		if (hasNegativeCycle) {
			sb.append(-1).append("\n");
		} else {
			for (int i = 2; i <= N; i++) {
				if (dist[i] == Long.MAX_VALUE) {
					sb.append(-1).append("\n");
				} else {
					sb.append(dist[i]).append("\n");
				}
			}
		}

		System.out.print(sb.toString());
	}

	static boolean bellmanFord() {
		Arrays.fill(dist, Long.MAX_VALUE);

		dist[1] = 0;

		for (int i = 0; i < N - 1; i++) {
			for (Edge edge : edges) {
				if (dist[edge.from] == Long.MAX_VALUE) {
					continue;
				}

				if (dist[edge.to] > dist[edge.from] + edge.cost) {
					dist[edge.to] = dist[edge.from] + edge.cost;
				}
			}
		}

		for (Edge edge : edges) {
			if (dist[edge.from] != Long.MAX_VALUE && dist[edge.to] > dist[edge.from] + edge.cost) {
				return true;
			}
		}
		return false;
	}
}

class Edge {
	int from, to, cost;

	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

}