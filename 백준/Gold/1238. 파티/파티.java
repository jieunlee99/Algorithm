
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, X;
	static int[] dist;
	static boolean[] visited;
	static List<Road>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 도로 수
		X = Integer.parseInt(st.nextToken()); // 목적지 (집->X, X->집 왕복 비용 구해야 함)

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
			int t = Integer.parseInt(st.nextToken());
			adjList[a].add(new Road(b, t));
		}

		int maxTime = -1;
		for (int i = 1; i <= N; i++) {
			if (i == X) {
				continue;
			}
			int time = dijkstra(i, X) + dijkstra(X, i);
			maxTime = Math.max(maxTime, time);
		}

		System.out.println(maxTime);
	}

	static int dijkstra(int start, int end) {
		PriorityQueue<Road> pq = new PriorityQueue<>();

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.offer(new Road(start, 0));

		while (!pq.isEmpty()) {
			Road current = pq.poll();

			for (Road next : adjList[current.to]) {
				if (dist[next.to] > next.time + current.time) {
					dist[next.to] = next.time + current.time;
					pq.offer(new Road(next.to, dist[next.to]));
				}
			}
		}

		return dist[end];
	}

	static class Road implements Comparable<Road> {
		int to, time;

		public Road(int to, int time) {
			this.to = to;
			this.time = time;
		}

		@Override
		public int compareTo(Road o) {
			return time - o.time;
		}

	}
}
