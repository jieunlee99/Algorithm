
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] parent;
	static Star[] stars;
	static List<Edge> edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		stars = new Star[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars[i] = new Star(x, y);
		}

		edges = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				double dist = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
				edges.add(new Edge(i, j, dist));
			}
		}

		Collections.sort(edges, Comparator.comparingDouble(Edge::getDist));

		double sum = 0;
		for (Edge edge : edges) {
			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				sum += edge.dist;
			}
		}

		System.out.printf("%.2f", sum);
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

	static class Star {
		double x, y;

		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge {
		int from, to;
		double dist;

		public Edge(int from, int to, double dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		public double getDist() {
			return dist;
		}
	}
}
