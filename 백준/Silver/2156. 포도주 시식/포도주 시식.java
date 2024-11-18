import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] input;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		input = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		dp = new int[N + 1];

		dp[0] = 0;
		dp[1] = input[1];
		if (N > 1) {
			dp[2] = input[1] + input[2];
		}

		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + input[i], dp[i - 3] + input[i] + input[i - 1]));
		}

		System.out.println(dp[N]);
	}

}
