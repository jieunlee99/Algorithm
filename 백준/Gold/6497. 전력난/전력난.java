
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int M, N;
	static int[] parent;
	static Edge[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 집의 수
			N = Integer.parseInt(st.nextToken()); // 길의 수

			// 0 0 입력 시 종료
			if (M == 0 && N == 0)
				break;

			parent = new int[M];
			for (int i = 0; i < M; i++) {
				parent[i] = i;
			}

			edges = new Edge[N];
			long currentTotalExpense = 0L;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(x, y, z);
				currentTotalExpense += z;
			}

			Arrays.sort(edges, Comparator.comparingInt(Edge::getDist));

			long minTotalExpense = 0L;

			for (Edge edge : edges) {
				if (find(edge.from) != find(edge.to)) {
					union(edge.from, edge.to);
					minTotalExpense += edge.dist;
				}
			}

			System.out.println(currentTotalExpense - minTotalExpense);
		}
	}

	static int find(int num) {
		if (parent[num] == num)
			return num;
		return parent[num] = find(parent[num]);
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		parent[aRoot] = bRoot;
	}

	static class Edge {
		int from, to, dist;

		public Edge(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		public int getDist() {
			return dist;
		}
	}
}
