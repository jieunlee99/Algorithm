import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int A, B;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;

	static 
	class Info {
		int idx, cnt;

		public Info(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		M = Integer.parseInt(br.readLine());

		// x와 y는 부모 자식 관계
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adjList[x].add(y);
			adjList[y].add(x);
		}

		int answer = bfs(A, B);

		System.out.println(answer);
	}

	static int bfs(int start, int end) {
		visited = new boolean[N + 1];

		Queue<Info> queue = new LinkedList<>();
		visited[start] = true;
		queue.add(new Info(start, 0));

		while (!queue.isEmpty()) {
			Info currentInfo = queue.poll();
			int idx = currentInfo.idx;
			int cnt = currentInfo.cnt;

			if (idx == end) {
				return cnt;
			}

			for (int next : adjList[idx]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(new Info(next, cnt + 1));
				}
			}
		}

		return -1;
	}

}
