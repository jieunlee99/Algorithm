import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static List<Edge> edges;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();

		// 이미 가중치 순서대로 간선을 주기 때문에 정렬 필요 x
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			edges.add(new Edge(x, y, i));
		}

		StringBuilder sb = new StringBuilder();
		boolean isPossible = true;

		for (int k = 0; k < K; k++) {
			if (!isPossible) {
				sb.append(0).append(" ");
				continue;
			}

			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}

			int weight = 0;
			int cnt = 0;

			for (int i = k; i < M; i++) {
				Edge e = edges.get(i);
				if (find(e.x) != find(e.y)) {
					union(e.x, e.y);
					weight += e.w;
					cnt++;
				}
			}

			if (cnt == N - 1) {
				sb.append(weight).append(" ");
			} else {
				sb.append(0).append(" ");
				isPossible = false;
			}
		}

		System.out.println(sb.toString().trim());
	}

	public static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);

		if (xRoot != yRoot) {
			parent[yRoot] = xRoot;
		}
	}
}

class Edge {
	int x, y, w;

	public Edge(int x, int y, int w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}
}