
import java.io.*;
import java.util.*;

public class Main {

	static final int MAX_VALUE = 100_000;

	static int N, K;

	static int[] coins;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		coins = new int[N + 1];
		dp = new int[K + 1];

		dp[0] = 1; // 0은 무조건 1가지 가능

		for (int i = 1; i <= N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
			for (int j = coins[i]; j <= K; j++) {
				dp[j] += dp[j - coins[i]];
			}
		}

		System.out.println(dp[K]);

	}

}
