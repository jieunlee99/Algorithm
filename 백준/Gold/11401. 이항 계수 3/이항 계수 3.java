import java.io.*;
import java.util.*;

public class Main {

	static final int MOD = 1_000_000_007;
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		long temp1 = factorial(N);
		long temp2 = factorial(K) * factorial(N - K) % MOD;

		System.out.println(temp1 * pow(temp2, MOD - 2) % MOD);
	}

	static long factorial(long num) {
		long fact = 1L;
		for (long i = 1; i <= num; i++) {
			fact = (fact * i) % MOD;
		}
		return fact;
	}

	static long pow(long num, long exp) {
		if (exp == 1) {
			return num % MOD;
		}

		long temp = pow(num, exp / 2);
		if (exp % 2 == 1) {
			return (temp * temp % MOD) * num % MOD;
		}
		return temp * temp % MOD;
	}
}
