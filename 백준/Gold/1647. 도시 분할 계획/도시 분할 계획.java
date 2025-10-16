import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 마을을 두 개로 분할할 계획 -> 유지비의 합을 최소로 하고자 함

public class Main {

	static int N, M;
	static Edge[] edges;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 집의 개수
		M = Integer.parseInt(st.nextToken()); // 길의 개수

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		edges = new Edge[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(a, b, c);
		}

		// edge 유지비 오름차순 정렬
		Arrays.sort(edges, Comparator.comparing(Edge::getCost));

		// 일단 모든 집을 연결한 뒤, 가장 비싼 유지비의 경로를 제거해버린다.
		int totalExpense = 0;
		int maxExpenseRoad = -1;
		for (Edge edge : edges) {
			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				totalExpense += edge.cost;
				maxExpenseRoad = Math.max(maxExpenseRoad, edge.cost);
			}
		}
		totalExpense -= maxExpenseRoad;

		System.out.println(totalExpense);
	}

	static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		public int getCost() {
			return cost;
		}
	}

	static int find(int num) {
		if (parent[num] == num) {
			return num;
		}
		return parent[num] = find(parent[num]);
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		parent[aRoot] = bRoot;
	}
}
