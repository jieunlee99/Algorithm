import java.io.*;
import java.util.*;

public class Main {

	static int F, S, G, U, D;
	static boolean[] visited;

	static class Info {
		int idx, cnt;

		public Info(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken());

		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());

		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		int answer = bfs(S, G);

		if (answer == -1) {
			System.out.println("use the stairs");
		} else {
			System.out.println(answer);
		}

	}

	static int bfs(int start, int end) {

		visited = new boolean[F + 1];

		Queue<Info> queue = new LinkedList<>();
		queue.add(new Info(start, 0));
		visited[start] = true;

		while (!queue.isEmpty()) {
			Info current = queue.poll();
			int idx = current.idx;
			int cnt = current.cnt;

			if(idx == end) {
				return cnt;
			}
			
			int[] nextArr = { idx + U, idx - D };

			for (int next : nextArr) {
				if (1 <= next && next <= F && !visited[next]) {
					queue.add(new Info(next, cnt + 1));
					visited[next] = true;
				}
			}
		}

		return -1;
	}
}