import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;

	static int maxSafeArea = 0;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		// 0(빈 칸), 1(벽), 2(바이러스)
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		buildWall(0);
		
		System.out.println(maxSafeArea);
	}

	public static void buildWall(int wall) {
		if (wall == 3) {
			spreadVirus();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					buildWall(wall + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	public static void spreadVirus() {
		Queue<int[]> queue = new LinkedList<>();

		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = map[i][j];
				if (arr[i][j] == 2) {
					queue.offer(new int[] { i, j });
				}
			}
		}

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				if (arr[nx][ny] == 0) {
					arr[nx][ny] = 2;
					queue.offer(new int[] { nx, ny });
				}
			}
		}

		int safeArea = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					safeArea++;
				}
			}
		}

		maxSafeArea = Math.max(safeArea, maxSafeArea);
	}
}
