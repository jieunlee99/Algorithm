import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();

	static int N, M, V;

	static boolean[] visited;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		adjList = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(adjList[i]);
		}

		dfs(V);
		sb.append("\n");

		Arrays.fill(visited, false);
		bfs(V);
		sb.append("\n");

		System.out.print(sb.toString());
	}

	static void dfs(int current) {
		visited[current] = true;
		sb.append(current).append(" ");
		for (int next : adjList[current]) {
			if (!visited[next]) {
				dfs(next);
			}
		}
	}

	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			sb.append(current).append(" ");

			for (int next : adjList[current]) {
				if (!visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
	}
}
