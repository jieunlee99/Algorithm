
// 최소 신장 트리 (MST) 
// 그래프의 모든 정점을 사이클 없이 잇는 트리에서 간선의 가중치 합이 최소인 트

import java.util.*;
import java.io.*;

//1. 크루스칼 알고리즘 - 간선 중심으로 최소 신장 트리를 구하는 알고리즘 (간선이 적을 때 유리)
//
//public class Main {
//
//	static int V, E;
//	static int[] parent;
//	static PriorityQueue<Edge> queue;
//
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		V = Integer.parseInt(st.nextToken()); // 정점 개수
//		E = Integer.parseInt(st.nextToken()); // 간선 개수
//
//		queue = new PriorityQueue<>();
//		parent = new int[V + 1];
//
//		// 부모 노드 초기화
//		for (int i = 1; i <= V; i++) {
//			parent[i] = i;
//		}
//
//		// 간선 정보 입력
//		for (int i = 0; i < E; i++) {
//			st = new StringTokenizer(br.readLine());
//			int v1 = Integer.parseInt(st.nextToken());
//			int v2 = Integer.parseInt(st.nextToken());
//			int w = Integer.parseInt(st.nextToken());
//
//			queue.offer(new Edge(v1, v2, w));
//		}
//
//		int weightSum = 0;
//
//		while (!queue.isEmpty()) {
//			Edge current = queue.poll(); // 가중치가 가장 작은 간
//			
//			if(find(current.v1) != find(current.v2)) {
//				union(current.v1, current.v2);
//				weightSum += current.weight;
//			}
//		}
//		
//		System.out.println(weightSum);
//	}
//
//	static void union(int a, int b) {
//		int aRoot = find(a);
//		int bRoot = find(b);
//
//		if (aRoot < bRoot) {
//			parent[bRoot] = aRoot;
//		} else {
//			parent[aRoot] = bRoot;
//		}
//	}
//
//	static int find(int n) {
//		if (parent[n] == n) {
//			return n;
//		} else {
//			return parent[n] = find(parent[n]);
//		}
//	}
//}
//
//class Edge implements Comparable<Edge> {
//	int v1;
//	int v2;
//	int weight;
//
//	public Edge(int v1, int v2, int weight) {
//		super();
//		this.v1 = v1;
//		this.v2 = v2;
//		this.weight = weight;
//	}
//
//	@Override
//	public int compareTo(Edge e) {
//		return weight - e.weight;
//	}
//}

//2. 프림 알고리즘 - 정점 중심으로 최소 신장 트리를 구하는 알고리즘 (정점이 적을 때 유리)

public class Main {

	static int V, E;
	static boolean[] visited;
	static PriorityQueue<Node> queue;
	static ArrayList<ArrayList<Node>> nodeList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 개수
		E = Integer.parseInt(st.nextToken()); // 간선 개수

		visited = new boolean[V + 1];
		
		queue = new PriorityQueue<>();
		
		nodeList = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			nodeList.add(new ArrayList<>());
		}


		// 간선 정보 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			nodeList.get(v1).add(new Node(v2, w));
			nodeList.get(v2).add(new Node(v1, w));
		}

		// 시작점 추가
		queue.offer(new Node(1, 0));
		
		int weightSum = 0;

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			
			if(visited[current.to]) {
				continue;
			} 
			visited[current.to] = true;
			weightSum += current.weight;
			
			// 해당 노드에 연결된 노드 중에서 방문한 적이 없는 노드를 큐에 추가함
			for(Node next : nodeList.get(current.to)) {
				if(!visited[next.to]) {
					queue.offer(next);
				}
			}
		}

		System.out.println(weightSum);
	}
}

class Node implements Comparable<Node> {
	int to;
	int weight;

	public Node(int to, int weight) {
		super();
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node node) {
		return weight - node.weight;
	}
}