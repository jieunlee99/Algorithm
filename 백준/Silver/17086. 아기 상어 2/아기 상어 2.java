import java.io.*;
import java.util.*;

public class Main {

	static int N, M;

	static int[][] map;
	static boolean[][] visited;
	static int[][] dist;

	static int[] dx = { 1, 1, 1, 0, 0, -1, -1, -1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		dist = new int[N][M];

		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					visited[i][j] = true;
					queue.add(new int[] { i, j });
				}
			}
		}

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int x = current[0];
			int y = current[1];

			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (isInRange(nx, ny) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dist[nx][ny] = dist[x][y] + 1;
					queue.add(new int[] { nx, ny });
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				answer = Math.max(answer, dist[i][j]);
			}
		}

		System.out.println(answer);
	}

	static boolean isInRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}
