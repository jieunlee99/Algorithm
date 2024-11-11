import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] dp; // dp[i][j]는 i번째부터 j번째 행렬까지 곱셈하는 최소 비용
	static int[] r, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1][N + 1];
		r = new int[N + 1];
		c = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
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

		bw.write(dp[1][N] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
