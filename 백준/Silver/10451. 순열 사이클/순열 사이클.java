import java.io.*;
import java.util.*;

public class Main {

	static int T;
	static int N;
	static int[] nums;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());

			nums = new int[N + 1];
			visited = new boolean[N + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			int cnt = 0;
			for (int current = 1; current <= N; current++) {
				if (!visited[current]) {
					visited[current] = true;
					int next = nums[current];
					while (true) {
						if (visited[next]) {
							cnt++;
							break;
						}
						visited[next] = true;
						next = nums[next];
					}
				}
			}

			sb.append(cnt).append("\n");
		}

		System.out.print(sb);
	}

}
