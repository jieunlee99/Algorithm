
import java.io.*;
import java.util.*;

public class Main {

	static final int[] dx = { 1, 0, -1, 0 };
	static final int[] dy = { 0, 1, 0, -1 };

	static int N, M;
	
	static int[][] map;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		dp = new long[N + 1][M + 1];

		// dp 배열을 -1로 초기화
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], -1);
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bw.write(findPaths(1, 1) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	static long findPaths(int x, int y) {
		// 도착점에 도달했을 때
		if (x == N && y == M) {
			return 1;
		}

		// 이미 계산된 경우
		if (dp[x][y] != -1) {
			return dp[x][y];
		}

		// 초기화
		dp[x][y] = 0;

		// 상하좌우 탐색
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			// 다음 좌표가 유효하고 내리막길일 때
			if (isInRange(nx, ny) && map[nx][ny] < map[x][y]) {
				dp[x][y] += findPaths(nx, ny);
			}
		}

		return dp[x][y];
	}

	static boolean isInRange(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= M;
	}
}
