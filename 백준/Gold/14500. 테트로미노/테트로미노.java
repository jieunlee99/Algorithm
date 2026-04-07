import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;

	static int[][] board;
	static boolean[][] visited;

	static int maxSum = Integer.MIN_VALUE;

	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				visited[i][j] = true;
				dfs(i, j, 1, board[i][j]);
				visited[i][j] = false;

				combi(i, j, 0, 0, board[i][j]);
			}
		}
		
		System.out.println(maxSum);
	}

	// 한 점을 고르고 그 점에 인접한 세 개의 점을 선택함
	// ㅗ ㅜ ㅓ ㅏ
	public static void combi(int x, int y, int depth, int start, int sum) { // ㅓ, ㅜ, ㅏ, ㅗ 테트로미노
		if (depth == 3) {
			maxSum = Math.max(maxSum, sum);
			return;
		}

		for (int i = start; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
				combi(x, y, depth + 1, i + 1, sum + board[nx][ny]);
			}
		}
	}

	public static void dfs(int x, int y, int depth, int sum) {
		if (depth == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
				continue;
			}

			if (visited[nx][ny]) {
				continue;
			}

			visited[nx][ny] = true;
			dfs(nx, ny, depth + 1, sum + board[nx][ny]);
			visited[nx][ny] = false;
		}
	}
}
