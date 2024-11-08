import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int A, B, C;

	static long[] dist;
	static ArrayList<Edge> edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시 수
		M = Integer.parseInt(st.nextToken()); // 버스 수

		dist = new long[N + 1];
		edges = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			edges.add(new Edge(A, B, C));
		}

		bellmanFord();

		if (hasNegativeCycle()) {
			bw.write("-1\n");
		} else {
			for (int i = 2; i <= N; i++) {
				if (dist[i] == Long.MAX_VALUE) {
					bw.write("-1\n");
				} else {
					bw.write(dist[i] + "\n");
				}
			}
		}

		bw.flush();

		bw.close();
		br.close();
	}

	static void bellmanFord() {
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[1] = 0; // start = 1

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
	}

	static boolean hasNegativeCycle() {
		dist[1] = 0; // start = 1

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