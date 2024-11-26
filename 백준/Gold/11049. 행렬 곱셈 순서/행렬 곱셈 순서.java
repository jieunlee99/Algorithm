import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] r, c;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		r = new int[N + 1];
		c = new int[N + 1];
		dp = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			r[i] = Integer.parseInt(st.nextToken());
			c[i] = Integer.parseInt(st.nextToken());
		}

		for (int len = 2; len <= N; len++) {
			for (int i = 1; i <= N - len + 1; i++) {
				int j = i + len - 1;

				dp[i][j] = Integer.MAX_VALUE;

				for (int k = i; k < j; k++) {
					int cost = dp[i][k] + dp[k + 1][j] + r[i] * c[k] * c[j];
					dp[i][j] = Math.min(dp[i][j], cost);
				}
			}
		}

		System.out.println(dp[1][N]);
	}
}