import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] dp;
	static final int MOD = 10007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1];
		dp[0] = dp[1] = 1;

		System.out.println(recur(N));
	}

	static int recur(int n) {
		if (dp[n] == 0) {
			dp[n] = (recur(n - 1) + recur(n - 2)) % MOD;
		}
		return dp[n];
	}
}
