import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + r;
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + g;
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + b;
		}

		System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));

	}
}
