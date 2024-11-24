
import java.io.*;
import java.util.*;

public class Main {

	static final int MOD = 1000;

	static int N;
	static long B;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());

		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()) % MOD;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		int[][] result = pow(arr, B);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	static int[][] pow(int[][] arr, long exp) {
		if (exp == 1) {
			return arr;
		}

		int[][] temp = pow(arr, exp / 2);
		temp = multiply(temp, temp);
		if (exp % 2 == 1) {
			temp = multiply(temp, arr);
		}
		return temp;
	}

	static int[][] multiply(int[][] a, int[][] b) {
		int[][] mul = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					mul[i][j] += a[i][k] * b[k][j];
					mul[i][j] %= MOD;
				}
			}
		}

		return mul;
	}
}
