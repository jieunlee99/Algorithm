import java.io.*;
import java.util.*;

public class Main {

	static int N, D; // N: 지름길 개수, D: 고속도로 길이
	static List<List<Node>> graph = new ArrayList<>(); // 그래프 (인접 리스트)
	static int[] distance; // 최단 거리 저장 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 지름길 개수
		D = Integer.parseInt(st.nextToken()); // 고속도로 길이

		// 🚀 [1] 그래프 초기화 (0~D까지)
		for (int i = 0; i <= D; i++) {
			graph.add(new ArrayList<>());
		}

		// 🚀 [2] 거리 배열 초기화 (최대값으로 설정)
		distance = new int[D + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		// 🚀 [3] 지름길 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 시작점
			int b = Integer.parseInt(st.nextToken()); // 끝점
			int w = Integer.parseInt(st.nextToken()); // 거리

			// 지름길이 고속도로 끝을 넘어서면 무시
			if (b > D) continue;

			// 지름길 저장 (a → b, 거리 w)
			graph.get(a).add(new Node(b, w));
		}

		// 🚀 [4] 다익스트라 알고리즘 실행
		dijkstra();

		// 🚀 [5] 최단 거리 출력
		System.out.println(distance[D]);
	}

	// 🚀 [6] 다익스트라 알고리즘 (우선순위 큐 이용)
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
		pq.offer(new Node(0, 0)); // 시작점(0, 거리 0)
		distance[0] = 0;

		while (!pq.isEmpty()) {
			Node current = pq.poll(); // 가장 가까운 노드 꺼내기
			int curPos = current.node; // 현재 위치
			int curDist = current.weight; // 현재까지 거리

			// 이미 최적 거리라면 스킵
			if (distance[curPos] < curDist) continue;

			// 1칸 이동하는 경우 (curPos → curPos + 1)
			if (curPos + 1 <= D && distance[curPos + 1] > curDist + 1) {
				distance[curPos + 1] = curDist + 1;
				pq.offer(new Node(curPos + 1, distance[curPos + 1]));
			}

			// 지름길 탐색 (현재 위치에서 이동 가능한 지름길)
			for (Node next : graph.get(curPos)) {
				int nextPos = next.node; // 지름길 도착점
				int nextDist = curDist + next.weight; // 현재 거리 + 지름길 거리

				if (distance[nextPos] > nextDist) {
					distance[nextPos] = nextDist;
					pq.offer(new Node(nextPos, nextDist));
				}
			}
		}
	}
}

// 🚀 [7] 노드 클래스 (우선순위 큐에서 사용)
class Node {
	int node;
	int weight;

	public Node(int node, int weight) {
		this.node = node;
		this.weight = weight;
	}
}