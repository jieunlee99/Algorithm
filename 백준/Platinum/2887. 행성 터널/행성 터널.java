
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] parent;
	static Planet[] planets;
	static List<Edge> edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		parent = new int[N];
		for (int i = 0; i < N; i++)
			parent[i] = i;

		planets = new Planet[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			planets[i] = new Planet(i, x, y, z);
		}

		edges = new ArrayList<>();

		// x축 기준 정렬 후 인접 행성 연결
		Arrays.sort(planets, Comparator.comparingInt(p -> p.x));
		for (int i = 0; i < N - 1; i++) {
			edges.add(new Edge(planets[i].index, planets[i + 1].index, Math.abs(planets[i].x - planets[i + 1].x)));
		}

		// y축 기준 정렬 후 인접 행성 연결
		Arrays.sort(planets, Comparator.comparingInt(p -> p.y));
		for (int i = 0; i < N - 1; i++) {
			edges.add(new Edge(planets[i].index, planets[i + 1].index, Math.abs(planets[i].y - planets[i + 1].y)));
		}

		// z축 기준 정렬 후 인접 행성 연결
		Arrays.sort(planets, Comparator.comparingInt(p -> p.z));
		for (int i = 0; i < N - 1; i++) {
			edges.add(new Edge(planets[i].index, planets[i + 1].index, Math.abs(planets[i].z - planets[i + 1].z)));
		}

		// 비용 오름차순 정렬
		edges.sort(Comparator.comparingInt(e -> e.cost));

		long totalCost = 0;
		int connected = 0;

		for (Edge edge : edges) {
			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				totalCost += edge.cost;
				connected++;

				if (connected == N - 1)
					break; // MST 완성
			}
		}

		System.out.println(totalCost);
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b)
			parent[a] = b;
	}

	static class Planet {
		int index, x, y, z;

		Planet(int index, int x, int y, int z) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Edge {
		int from, to, cost;

		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
