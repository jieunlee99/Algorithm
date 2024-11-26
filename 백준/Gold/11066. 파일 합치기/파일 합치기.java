
import java.io.*;
import java.util.*;

public class Main {

	static int T, N;
	static int[] files;
	static int[][] dp; // dp[i][j]: i번째 파일부터 j번째 파일까지 합치는데 드는 최소 비용

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());

			files = new int[N + 1];
			dp = new int[N + 1][N + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				files[i] = files[i - 1] + Integer.parseInt(st.nextToken());
			}

			for (int len = 2; len <= N; len++) {
				for (int i = 1; i <= N - len + 1; i++) {
					int j = i + len - 1;

					dp[i][j] = Integer.MAX_VALUE;

					for (int k = i; k < j; k++) {
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + (files[j] - files[i - 1]));
					}
				}
			}

			sb.append(dp[1][N]).append("\n");
		}

		System.out.println(sb.toString());

	}
}
