import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] map;
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		dp = new long[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 반드시 오른쪽이나 아래쪽으로만 이동해야 함
		// 현재 칸에 적혀있는 수만큼 이동
		// (1, 1) -> (N, N)
		// 도착지의 값은 무조건 0

		dp[0][0] = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int jump = map[i][j];

				if (jump == 0) {
					break;
				}

				if (j + jump < N) {
					dp[i][j + jump] += dp[i][j];
				}

				if (i + jump < N) {
					dp[i + jump][j] += dp[i][j];
				}
			}
		}

		System.out.println(dp[N - 1][N - 1]);

	}
}
