import java.util.*;
import java.io.*;

public class Main {

	static int N;

	static int[][] arr;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		dp = new int[N][N];

		// 삼각형 배열 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 마지막 행을 dp 배열의 초기값으로 설정
		for (int i = 0; i < N; i++) {
			dp[N - 1][i] = arr[N - 1][i];
		}

		// bottom up 방식으로 합 계산
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + arr[i][j];
			}
		}

		bw.write(dp[0][0] + "\n");

		bw.flush();

		bw.close();
		br.close();
	}
}
