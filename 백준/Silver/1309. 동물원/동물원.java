
import java.io.*;
import java.util.*;

public class Main {

	static final int MOD = 9901;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		long[][] dp = new long[N + 1][3];

		// dp[i][j] : i번째 줄의 j번째 칸에 동물을 놓을 수 있는 경우의 수
		// j = 0 : 아무 동물도 놓지 않음
		// j = 1 : 첫 번째 칸에 동물을 놓음
		// j = 2 : 두 번째 칸에 동물을 놓음

		dp[1][0] = dp[1][1] = dp[1][2] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
		}

		long answer = (dp[N][0] + dp[N][1] + dp[N][2]) % MOD;

		System.out.println(answer);
	}

}