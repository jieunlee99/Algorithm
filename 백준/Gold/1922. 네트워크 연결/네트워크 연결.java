
import java.util.*;
import java.io.*;

public class Main {

	static StringTokenizer st;
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		queue = new PriorityQueue<>();

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			queue.offer(new Edge(a, b, c));
		}

		int weightSum = 0;
		
		while(!queue.isEmpty()) {
			Edge current = queue.poll();
			
			if(find(current.v1) != find(current.v2)) {
				union(current.v1, current.v2);
				weightSum += current.weight;
			}
		}
		
		System.out.println(weightSum);
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

	static int find(int n) {
		if (parent[n] == n) {
			return n;
		} else {
			return parent[n] = find(parent[n]);
		}
	}
}

class Edge implements Comparable<Edge> {

	int v1, v2;
	int weight;

	public Edge(int v1, int v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge edge) {
		// TODO Auto-generated method stub
		return weight - edge.weight;
	}

}