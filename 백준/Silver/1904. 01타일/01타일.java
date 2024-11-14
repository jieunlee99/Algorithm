import java.util.*;
import java.io.*;

public class Main {

	static final int MAX = 1000000;
	static int N;
	static int[] dp = new int[MAX + 1];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= MAX; i++) {
			dp[i] = -1;
		}

		System.out.println(solution(N));
	}

	static int solution(int n) {
		if (dp[n] == -1) {
			dp[n] = (solution(n - 1) + solution(n - 2)) % 15746;
		}
		return dp[n];
	}
}