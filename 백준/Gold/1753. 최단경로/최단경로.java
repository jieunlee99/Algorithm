

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라

public class Main {

	static int V, E, K;
	static PriorityQueue<Edge> pq;
	static int[] dist;
	static ArrayList<Edge>[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("src/DAY09/P1753/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수
		K = Integer.parseInt(br.readLine()); // 시작 정점의 번호

		pq = new PriorityQueue<>();
		dist = new int[V + 1];
		visited = new boolean[V + 1];
		adjList = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			adjList[i] = new ArrayList<>();
		}

		int u, v, w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			// u에서 v로 가는 가중치 w인 간선이 존재한다.
			// 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수 있다.
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			adjList[u].add(new Edge(v, w));
		}

		// 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다.
		// 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
		
		solution(K);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=V; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			} else {
				sb.append(dist[i]).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

	static void solution(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			if (visited[current.to]) {
				continue;
			}
			visited[current.to] = true;

			for (Edge next : adjList[current.to]) {
				if (dist[next.to] > next.weight + current.weight) {
					dist[next.to] = next.weight + current.weight;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}
}

class Edge implements Comparable<Edge>{
	int to;
	int weight;

	public int getTo() {
		return to;
	}

	public int getWeight() {
		return weight;
	}

	public Edge(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge e) {
		// TODO Auto-generated method stub
		return this.weight-e.weight;
	}
}
