
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][N + 1];

		dp[1][0] = 1;
		dp[1][1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i][0] = 1;
			for (int j = 1; j < i; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				dp[i][j] %= 10_007;
			}
			dp[i][i] = 1;
		}

		System.out.println(dp[N][K]);
	}

}
