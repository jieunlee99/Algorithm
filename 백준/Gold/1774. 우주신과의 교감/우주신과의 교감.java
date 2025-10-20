

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parent;
	static List<Edge> edges;
	static Pos[] pos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 우주신들의 개수
		M = Integer.parseInt(st.nextToken()); // 연결된 신들과의 통로 개수

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		pos = new Pos[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pos[i] = new Pos(x, y);
		}

		edges = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				double dist = Math.sqrt(Math.pow(pos[i].x - pos[j].x, 2) + Math.pow(pos[i].y - pos[j].y, 2));
				edges.add(new Edge(i, j, dist));
			}
		}
		Collections.sort(edges, Comparator.comparingDouble(Edge::getDist));

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		double answer = 0;
		for (Edge edge : edges) {
			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				answer += edge.dist;
			}
		}

		System.out.printf("%.2f", Math.round(answer * 100) / 100.0);
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		parent[aRoot] = bRoot;
	}

	static int find(int num) {
		if (parent[num] == num) {
			return num;
		}
		return parent[num] = find(parent[num]);
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
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
