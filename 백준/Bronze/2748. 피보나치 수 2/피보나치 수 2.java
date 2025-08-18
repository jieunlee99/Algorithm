import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long[] dp = new long[91];

		dp[0] = 0L;
		dp[1] = 1L;

		int N = Integer.parseInt(br.readLine());
		
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		System.out.println(dp[N]);
	}

}
