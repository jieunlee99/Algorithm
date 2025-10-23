
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dist;
	static boolean[] visited;
	static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()); // start
		int b = Integer.parseInt(st.nextToken()); // end

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		dist = new int[N + 1];
		visited = new boolean[N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}

		Queue<Integer> queue = new LinkedList<>();

		Arrays.fill(dist, Integer.MAX_VALUE);

		queue.offer(a);
		dist[a] = 0;
		visited[a] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int next : adjList[current]) {
				if (!visited[next]) {
					dist[next] = Math.min(dist[next], dist[current] + 1);
					visited[next] = true;
					queue.offer(next);
				}
			}
		}

		if (dist[b] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(dist[b]);
		}
	}

}
