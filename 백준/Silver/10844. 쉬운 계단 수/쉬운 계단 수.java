import java.io.*;
import java.util.*;

public class Main {

	static final int MOD = 1_000_000_000;
	static int N;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp = new long[N + 1][10];
		
		initDP();
		
		// 제일 큰 자리에 수를 하나씩 추가하는 방식으로 dp
		long result = 0;
		for (int i = 1; i <= 9; i++) {
			result += recur(N, i);
		}
		
		System.out.println(result%MOD);
	}

	static void initDP() {
		// 한 자리 수는 1로 초기화
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1L;
		}
	}

	static long recur(int digit, int value) {

		// 한 자리 수라면 확인하지 않아도 됨.
		if (digit == 1) {
			return dp[digit][value];
		}

		// 탐색하지 않았다면
		if (dp[digit][value] == 0) {
			
			// 0일 때는 1만 가능함
			if (value == 0) {
				dp[digit][value] = recur(digit - 1, 1);
			} 
			
			// 9일 때는 8만 가능함
			else if (value == 9) {
				dp[digit][value] = recur(digit - 1, 8);
			} 
			
			// 1~8은 +1, -1 둘다 가능
			else {
				dp[digit][value] = recur(digit - 1, value - 1) + recur(digit - 1, value + 1);
			}
		}

		return dp[digit][value] % MOD;
	}

}
