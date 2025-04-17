
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}

		// from에서 to로 갈 때 cost만큼의 비용이 든다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Node(to, cost));
		}

		// 시작점, 도착점
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		// 다익스트라 알고리즘: 모든 노드를 무한대로 초기화
		int[] dist = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		// 우선순위 큐 - 비용 내림차순 정렬
		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

		// 시작점 초기화
		pq.offer(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node current = pq.poll();

			// 현재 노드의 비용이 더 비싸다면 pass
			if (dist[current.idx] < current.cost) {
				continue;
			}

			// 현재 노드에서 갈 수 있는 모든 노드 탐색
			for (int i = 0; i < adjList.get(current.idx).size(); i++) {
				Node next = adjList.get(current.idx).get(i);

				// 비용이 더 싸다면 변경
				if (dist[next.idx] > current.cost + next.cost) {
					dist[next.idx] = current.cost + next.cost;
					pq.offer(new Node(next.idx, dist[next.idx]));
				}
			}
		}
		
		System.out.println(dist[end]);
	}

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

}
