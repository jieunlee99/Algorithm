
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int start, end;

	static int[] dist;
	static boolean[] visited;
	static List<Edge>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 도시의 개수
		M = Integer.parseInt(br.readLine()); // 버스의 개수

		dist = new int[N + 1];
		visited = new boolean[N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjList[a].add(new Edge(b, c));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijkstra();

		System.out.println(dist[end]);
	}

	static void dijkstra() {
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
				if (dist[next.to] > next.cost + current.cost) {
					dist[next.to] = next.cost + current.cost;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}

	}

	static class Edge implements Comparable<Edge> {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}

	}
}
