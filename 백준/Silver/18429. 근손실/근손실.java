
import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int cnt;
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 500);

		System.out.println(cnt);
	}

	static void dfs(int depth, int currentWeight) {
		if (depth == N) {
			cnt++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}

			int nextWeight = currentWeight - K + arr[i];
			if (nextWeight < 500) {
				continue;
			}

			visited[i] = true;
			dfs(depth + 1, nextWeight);
			visited[i] = false;
		}
	}

}
