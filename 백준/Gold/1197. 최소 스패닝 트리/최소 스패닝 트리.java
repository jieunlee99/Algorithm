
import java.io.*;
import java.util.*;

public class Main {

	static int V, E;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		parent = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			pq.offer(new Edge(from, to, cost));
		}

		int sum = 0;

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			if (find(current.from) != find(current.to)) {
				union(current.from, current.to);
				sum += current.cost;
			}
		}

		System.out.println(sum);
	}

	static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot < bRoot) {
			parent[bRoot] = aRoot;
		} else {
			parent[aRoot] = bRoot;
		}
	}

}

class Edge implements Comparable<Edge> {
	int from, to, cost;

	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.cost - o.cost;
	}
}
