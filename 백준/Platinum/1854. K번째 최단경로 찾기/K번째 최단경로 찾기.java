import java.util.*;
import java.io.*;

public class Main {

	static int n, m, k;
	static List<Edge>[] adjList;
	static PriorityQueue<Integer>[] shortestPaths; // 각 노드로의 k번째 최단 경로를 저장하는 우선순위 큐 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		initialize();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adjList[from].add(new Edge(to, cost));
		}

		findKthShortestPaths();

		for (int i = 1; i <= n; i++) {
			bw.write((shortestPaths[i].size() == k ? shortestPaths[i].peek() : -1) + "\n");
		}
		
		bw.flush();
		
		br.close();
		bw.close();
	}

	static void initialize() {
		adjList = new ArrayList[n + 1];
		shortestPaths = new PriorityQueue[n + 1];

		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList<>();
			shortestPaths[i] = new PriorityQueue<>(k, Collections.reverseOrder());
		}
	}

	// k번째 최단 경로를 찾는 다익스트라 변형 알고리즘
	static void findKthShortestPaths() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// start = 1
		pq.add(new Edge(1, 0));
		shortestPaths[1].add(0);

		while (!pq.isEmpty()) {
			Edge current = pq.poll(); // 현재 가장 짧은 경로를 가진 노드
			int currentNode = current.destination;
			int currentCost = current.cost;

			// 현재 노드에서 연결된 간선 탐색
			for (Edge next : adjList[currentNode]) {
				int newCost = currentCost + next.cost;

				// 다음 노드의 최단 경로가 k개 미만일 경우, 무조건 추가
				if (shortestPaths[next.destination].size() < k) {
					shortestPaths[next.destination].add(newCost);
					pq.add(new Edge(next.destination, newCost));
				} 
				
				// 다음 노드의 최단 경로가 이미 k개지만, k번째 경로보다 작은 경우 -> 기존 k	번째를 지운 후 새 값을 넣어
				else if (shortestPaths[next.destination].peek() > newCost) {
					shortestPaths[next.destination].poll(); // 
					shortestPaths[next.destination].add(newCost);
					pq.add(new Edge(next.destination, newCost));
				}
			}
		}
	}
}

class Edge implements Comparable<Edge> {
	int destination;
	int cost;

	public Edge(int destination, int cost) {
		this.destination = destination;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return cost - o.cost;
	}
}