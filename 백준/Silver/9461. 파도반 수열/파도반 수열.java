import java.util.*;
import java.io.*;

public class Main {

	static int T;
	static long[] dp = new long[101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Arrays.fill(dp, -1);
		dp[0] = 0L;
		dp[1] = dp[2] = dp[3] = 1L;
		
		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(p(N)).append("\n");
		}
		
		System.out.print(sb);

	}

	static long p(int n) {
		if(dp[n] == -1) {
			dp[n] = p(n-2)+p(n-3);
		}
		return dp[n];
	}
}
