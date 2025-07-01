
import java.io.*;
import java.util.*;

public class Main {

	static int T, N;

	static int[] S;
	static boolean[] visited, finished;

	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());

			S = new int[N + 1];
			visited = new boolean[N + 1];
			finished = new boolean[N + 1];
			cnt = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					dfs(i);
				}
			}

			sb.append(N - cnt).append("\n");
		}

		System.out.print(sb);
	}

	static void dfs(int cur) {
		visited[cur] = true;
		int next = S[cur];

		if (!visited[next]) {
			dfs(next);
		} else if (!finished[next]) {
			for (int i = next; i != cur; i = S[i]) {
				cnt++;
			}
			cnt++;
		}

		finished[cur] = true;

	}

}
