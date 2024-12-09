import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] dp;
	static int[] trace;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1];
		trace = new int[N + 1];

		dp[1] = 0;

		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + 1;
			trace[i] = i - 1;

			if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
				dp[i] = dp[i / 3] + 1;
				trace[i] = i / 3;
			}

			if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
				dp[i] = dp[i / 2] + 1;
				trace[i] = i / 2;
			}
		}

		sb.append(dp[N]).append("\n");
		while (N > 0) {
			sb.append(N).append(" ");
			N = trace[N];
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
