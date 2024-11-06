import java.util.*;
import java.io.*;

public class Main {

	static final int LOG = 17;

	static int N, K;
	static int A, B, C;
	static int D, E;

	static boolean[] visited;
	static int[] depth;
	static int[][] parent;
	static List<Road>[] adjList;

	static int[][] minDist;
	static int[][] maxDist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		visited = new boolean[N + 1];
		depth = new int[N + 1];
		parent = new int[LOG + 1][N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		minDist = new int[LOG + 1][N + 1];
		maxDist = new int[LOG + 1][N + 1];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			adjList[A].add(new Road(B, C));
			adjList[B].add(new Road(A, C));
		}

		bfs(1);

		findAncestors();

		K = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		int[] result;

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			result = lca(D, E);
			sb.append(result[0]).append(" ").append(result[1]).append("\n");
		}

		bw.write(sb.toString());

		bw.flush();

		bw.close();
		br.close();
	}

	static void bfs(int root) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();

		queue.add(root);
		depth[root] = 0;
		visited[root] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			int next;

			for (Road road : adjList[current]) {
				next = road.to;

				if (!visited[next]) {
					visited[next] = true;
					parent[0][next] = current;
					depth[next] = depth[current] + 1;

					minDist[0][next] = road.dist;
					maxDist[0][next] = road.dist;

					queue.add(next);
				}
			}
		}
	}

	static void findAncestors() {
		for (int k = 1; k <= LOG; k++) {
			for (int v = 1; v <= N; v++) {
				parent[k][v] = parent[k - 1][parent[k - 1][v]];

				minDist[k][v] = Math.min(minDist[k - 1][v], minDist[k - 1][parent[k - 1][v]]);
				maxDist[k][v] = Math.max(maxDist[k - 1][v], maxDist[k - 1][parent[k - 1][v]]);
			}
		}
	}

	static int[] lca(int a, int b) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		if (depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}

		for (int k = LOG; k >= 0; k--) {
			if (depth[b] - depth[a] >= (1 << k)) {
				min = Math.min(min, minDist[k][b]);
				max = Math.max(max, maxDist[k][b]);
				b = parent[k][b];
			}
		}

		if (a == b) {
			return new int[] { min, max };
		}

		for (int k = LOG; k >= 0; k--) {
			if (parent[k][a] != parent[k][b]) {
				min = Math.min(min, Math.min(minDist[k][a], minDist[k][b]));
				max = Math.max(max, Math.max(maxDist[k][a], maxDist[k][b]));

				a = parent[k][a];
				b = parent[k][b];
			}
		}

		min = Math.min(min, Math.min(minDist[0][a], minDist[0][b]));
		max = Math.max(max, Math.max(maxDist[0][a], maxDist[0][b]));
		return new int[] { min, max };
	}
}

class Road {
	int to, dist;

	public Road(int to, int dist) {
		this.to = to;
		this.dist = dist;
	}
}