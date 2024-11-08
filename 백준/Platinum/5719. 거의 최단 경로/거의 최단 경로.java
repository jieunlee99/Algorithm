import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int S, D;
	static int U, V, P;

	static ArrayList<Node>[] adjList;
	static ArrayList<Integer>[] shortestPathList;
	static boolean[] visited;
	static int[] dist;
	static boolean[][] isShortestPath; // [from][to]

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0) {
				break;
			}

			adjList = new ArrayList[N];
			shortestPathList = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				adjList[i] = new ArrayList<>();
				shortestPathList[i] = new ArrayList<>();
			}
			visited = new boolean[N];
			dist = new int[N];
			isShortestPath = new boolean[N][N];

			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				U = Integer.parseInt(st.nextToken());
				V = Integer.parseInt(st.nextToken());
				P = Integer.parseInt(st.nextToken());
				adjList[U].add(new Node(V, P));
			}
			
			dijkstra(S); // 첫 번째 다익스트라로 최단 거리 탐색
			reverse(S, D); // 최단 거리에 사용된 간선을 모두 제외시
			dijkstra(S); // 두 번째 다익스트라로 거의 최단 거리 탐색
			
			bw.write((dist[D] == Integer.MAX_VALUE ? -1 : dist[D])+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		Arrays.fill(dist, Integer.MAX_VALUE);
	    Arrays.fill(visited, false); 
		
		dist[start] = 0;
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();

			if (visited[current.dest]) {
				continue;
			}

			visited[current.dest] = true;

			for (Node next : adjList[current.dest]) {
				// 최단 거리에 포함되는 간선의 후보가 되는 경우는 두 가지
				if (isShortestPath[current.dest][next.dest]) {
					// 역방향 간선 추가
					continue;
				}

				if (dist[next.dest] == next.cost + current.cost) {
					shortestPathList[next.dest].add(current.dest);
				} else if (dist[next.dest] > next.cost + current.cost) {
					dist[next.dest] = next.cost + current.cost;
					
					// 갱신
					shortestPathList[next.dest].clear();
					shortestPathList[next.dest].add(current.dest);
					
					pq.offer(new Node(next.dest, dist[next.dest]));
				}
			}
		}

	}

	// D -> S
	static void reverse(int start, int current) {
		if(start == current) {
			return;
		}
		
		for(int next:shortestPathList[current]) {
			if(!isShortestPath[next][current]) {
				isShortestPath[next][current] = true;
				reverse(start, next);
			}
		}
	}
}

class Node implements Comparable<Node> {
	int dest;
	int cost;

	public Node(int dest, int cost) {
		this.dest = dest;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return cost - o.cost;
	}

}