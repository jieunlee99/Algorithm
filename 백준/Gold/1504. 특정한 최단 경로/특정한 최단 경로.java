import java.io.*;
import java.util.*;

public class Main {

	static int N, E;

	static ArrayList<Edge>[] adjList;
	static boolean[] visited;
	static int[] dist;

	static final int INF = 200_000 * 1_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		visited = new boolean[N + 1];
		dist = new int[N + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			adjList[u].add(new Edge(v, dist));
			adjList[v].add(new Edge(u, dist));
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		// (case 1) 1 -> v1 -> v2 -> N
		int case1 = 0;
		case1 += dijkstra(1, v1);
		case1 += dijkstra(v1, v2);
		case1 += dijkstra(v2, N);

		// (case 2) 1 -> v2 -> v1 -> N
		int case2 = 0;
		case2 += dijkstra(1, v2);
		case2 += dijkstra(v2, v1);
		case2 += dijkstra(v1, N);

		if (case1 >= INF && case2 >= INF) {
			bw.write(-1 + "\n");
		} else {
			bw.write(Math.min(case1, case2) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	static int dijkstra(int from, int to) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		Arrays.fill(visited, false);
		Arrays.fill(dist, INF);

		dist[from] = 0;
		pq.add(new Edge(from, 0));

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			if (visited[current.to]) {
				continue;
			}

			visited[current.to] = true;

			for (Edge next : adjList[current.to]) {
				if (dist[next.to] > dist[current.to] + next.dist) {
					dist[next.to] = dist[current.to] + next.dist;
					pq.add(new Edge(next.to, dist[next.to]));
				}
			}
		}

		return dist[to];

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