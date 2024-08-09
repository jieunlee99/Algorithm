
import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parent;
	static Edge[] edges;

	static int sum = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("src/DAY08/P1922/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수

		parent = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			parent[n] = n;
		}

		edges = new Edge[M];

		StringTokenizer st;
		int a, b, c;

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			// a와 b를 연결하는 비용이 c
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			edges[m] = new Edge(a, b, c);
		}

		Arrays.sort(edges, Comparator.comparing(Edge::getCost));
//		System.out.println(Arrays.toString(edges));

		for (int m = 0; m < M; m++) {
			// 같은 set인지 검사
			Edge current = edges[m];
			if (find(current.from) != find(current.to)) {
				union(current.from, current.to);
				sum += current.cost;

			}
		}

		System.out.println(sum);
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		parent[aRoot] = bRoot;
	}

}

class Edge {
	int from, to, cost;

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public int getCost() {
		return cost;
	}

	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
	}
}