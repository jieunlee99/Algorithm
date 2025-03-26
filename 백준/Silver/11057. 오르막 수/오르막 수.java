import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] dp = new int[N + 1][10];

		// 길이가 1일 때 자기자신으로 무조건 오르막이 1개씩 있음
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}

		// 길이가 i이면서, 끝자리가 j일때 오르막 수 구하
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				// 이전 길이(i-1)일 때 끝 자리가 j보다 작은 모든 수들을 더함
				for (int k = 0; k <= j; k++) {
					dp[i][j] += dp[i - 1][k];
				}
				dp[i][j] %= 10007;
			}
		}

		long answer = 0;
		for (int i = 0; i < 10; i++) {
			answer += dp[N][i];
		}

		System.out.println(answer % 10007);
	}

}
