import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;

	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int n = 0; n < N; n++) {
			String input = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = input.charAt(m) - '0';
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {
		boolean[][][] visited = new boolean[N][M][2]; // 0: 벽을 부수지 않은 상태, 1: 벽을 부순 상태
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0, 0 }); // x, y, 벽 부순 여부
		visited[0][0][0] = true;

		int cnt = 1; // 시작 지점도 포함되므로 1부터 시작

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int s = 0; s < size; s++) {
				int[] current = queue.poll();
				int x = current[0];
				int y = current[1];
				int wallBroken = current[2];

				if (x == N - 1 && y == M - 1) { // 도착 지점
					return cnt;
				}

				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (isInRange(nx, ny)) {
						// 벽을 만나지 않은 경우
						if (map[nx][ny] == 0 && !visited[nx][ny][wallBroken]) {
							visited[nx][ny][wallBroken] = true;
							queue.add(new int[] { nx, ny, wallBroken });
						}

						// 벽을 만나고 아직 벽을 부술 기회가 있는 경우
						if (map[nx][ny] == 1 && wallBroken == 0 && !visited[nx][ny][1]) {
							visited[nx][ny][1] = true;
							queue.add(new int[] { nx, ny, 1 });
						}
					}
				}
			}

			cnt++;
		}

		return -1; // 도달할 수 없는 경우
	}

	static boolean isInRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}
