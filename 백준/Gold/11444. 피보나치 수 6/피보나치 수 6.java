import java.io.*;
import java.util.*;

public class Main {

	static final int MOD = 1_000_000_007;
	static long[][] arr = { { 1, 1 }, { 1, 0 } };
	static long N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Long.parseLong(br.readLine());
		
		System.out.println(pow(arr, N-1)[0][0]);
	}

	static long[][] pow(long[][] arr, long exp) {
		if (exp == 1 || exp == 0) {
			return arr;
		}

		long[][] temp = pow(arr, exp / 2);
		temp = multiply(temp, temp);
		if (exp % 2 == 1L) {
			temp = multiply(temp, arr);
		}
		return temp;

	}

	static long[][] multiply(long[][] a, long[][] b) {
		long[][] mul = new long[2][2];

		mul[0][0] = ((a[0][0] * b[0][0]) + (a[0][1] * b[1][0])) % MOD;
		mul[0][1] = ((a[0][0] * b[0][1]) + (a[0][1] * b[1][1])) % MOD;
		mul[1][0] = ((a[1][0] * b[0][0]) + (a[1][1] * b[1][0])) % MOD;
		mul[1][1] = ((a[1][0] * b[0][1]) + (a[1][1] * b[1][1])) % MOD;

		return mul;
	}
}
