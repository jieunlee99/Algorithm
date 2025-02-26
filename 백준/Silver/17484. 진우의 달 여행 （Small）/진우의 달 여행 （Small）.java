import java.io.*;
import java.util.*;

public class Main {

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		int[][][] dp = new int[N][M][3]; // 움직일 수 있는 방향이 세 방향임

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 기본값 세팅
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 3; j++) {
				dp[0][i][j] = map[0][i];
			}
		}

		// dp 계산
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j == 0) { // 맨 왼쪽일 때는 왼쪽이 없기 때문에 왼쪽에서 오는 방향은 이 문제의 최대값인 600보다 큰 값을 할당해준다.
					dp[i][j][0] = 700;
					dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + map[i][j];
					dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + map[i][j];
				} else if (j == M - 1) { // 맨 오른쪽일 때는 오른쪽이 없기 때문에 이 문제의 최대값인 600보다 큰 값을 할당해준다.
					dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + map[i][j];
					dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + map[i][j];
					dp[i][j][2] = 700;
				} else { // 일반적인 경우
					dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + map[i][j];
					dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + map[i][j];
					dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + map[i][j];
				}
			}
		}

		int min = 700;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 3; j++) {
				min = Math.min(min, dp[N - 1][i][j]);
			}
		}

		System.out.println(min);
	}
}
