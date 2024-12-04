import java.io.*;
import java.util.*;

public class Main {

	static int T;
	static int n, m, t;
	static int s, g, h;

	static ArrayList<Edge>[] adjList;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 정점 수
			m = Integer.parseInt(st.nextToken()); // 간선 수
			t = Integer.parseInt(st.nextToken()); // 목적지 후보 수

			adjList = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				adjList[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken()); // 시작점
			g = Integer.parseInt(st.nextToken()); // g, h를 무조건 들림
			h = Integer.parseInt(st.nextToken());

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				adjList[a].add(new Edge(b, d));
				adjList[b].add(new Edge(a, d));
			}

			ArrayList<Integer> possibleDestinations = new ArrayList<>();
			for (int i = 0; i < t; i++) {
				int x = Integer.parseInt(br.readLine());

				// 필수 경유하는 거리 계산
				int throughGH = dijkstra(s, g) + dijkstra(g, h) + dijkstra(h, x);
				int throughHG = dijkstra(s, h) + dijkstra(h, g) + dijkstra(g, x);

				// s에서 x까지의 최단 거리
				int shortest = dijkstra(s, x);

				if (shortest == throughGH || shortest == throughHG) {
					possibleDestinations.add(x);
				}
			}

			Collections.sort(possibleDestinations);

			for (int dest : possibleDestinations) {
				sb.append(dest).append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	static int dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		pq.add(new Edge(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			if (current.to == end) {
				return dist[end];
			}

			for (Edge next : adjList[current.to]) {
				if (dist[next.to] > dist[current.to] + next.dist) {
					dist[next.to] = dist[current.to] + next.dist;
					pq.add(new Edge(next.to, dist[next.to]));
				}
			}
		}

		return -1;
	}
}

class Edge implements Comparable<Edge> {
	int to, dist;

	public Edge(int to, int dist) {
		this.to = to;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		return dist - o.dist;
	}
}
