import java.util.*;
import java.io.*;

public class Main {

	static int[] dp = new int[11]; // 1 <= N < 11

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// dp 배열 초기화
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i <= 10; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}

		// 테스트케이스 처리
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N]).append("\n");
		}

		System.out.print(sb.toString());
	}
}
