import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[][] dp = new int[100_001][4];
		// 합이 3 이하인 경우에는 사전에 초기값 1을 넣어준다.
		dp[1][1] = dp[2][1] = dp[2][2] = dp[3][1] = dp[3][2] = dp[3][3] = 1;

		for (int j = 4; j <= 100_000; j++) {
			dp[j][1] = dp[j - 1][1];
			dp[j][2] = dp[j - 2][1] + dp[j - 2][2];
			dp[j][3] = dp[j - 3][1] + dp[j - 3][2] + dp[j - 3][3];
		}

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N][1]+dp[N][2]+dp[N][3]).append("\n");
		}
		
		System.out.print(sb);

	}

}
