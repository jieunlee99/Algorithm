import java.io.*;
import java.util.*;

public class Main {

	static int N, M;

	static int[][] map;
	static boolean[][] visited;
	static int[][] dp;

	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		dp = new int[N + 1][M + 1];

		for (int n = 1; n <= N; n++) {
			String input = br.readLine();
			for (int m = 1; m <= M; m++) {
				map[n][m] = input.charAt(m - 1) - '0';
			}
		}

		bfs();

		System.out.println(dp[N][M]);
	}

	static void bfs() {
		Queue<int[]> queue = new LinkedList<>();

		// (1, 1)부터 탐색 시작
		queue.offer(new int[] { 1, 1 });
		visited[1][1] = true;
		dp[1][1] = 1;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int x = current[0];
			int y = current[1];

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (isInRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
					queue.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
					dp[nx][ny] = dp[x][y] + 1;
				}
			}
		}

	}

	static boolean isInRange(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= M;
	}
}
