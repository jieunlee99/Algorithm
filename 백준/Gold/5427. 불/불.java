import java.io.*;
import java.util.*;

public class Main {

	static int T, w, h;
	static char[][] map;
	static Queue<int[]> me, fire;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken()); // 너비 (열 개수)
			h = Integer.parseInt(st.nextToken()); // 높이 (행 개수)

			map = new char[h][w]; 
			me = new LinkedList<>();
			fire = new LinkedList<>();

			// 입력 받기
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) { // j < h가 아니라 j < w
					if (map[i][j] == '@') {
						me.add(new int[] { i, j });
					} else if (map[i][j] == '*') {
						fire.add(new int[] { i, j });
					}
				}
			}

			// BFS 실행
			int result = bfs();
			if (result == -1) {
				sb.append("IMPOSSIBLE").append("\n");
			} else {
				sb.append(result).append("\n");
			}
		}
		System.out.print(sb);
	}

	static int bfs() {
		int time = 0;

		while (!me.isEmpty()) {
			time++;

			// 1. 불 확산
			int fireSize = fire.size();
			for (int i = 0; i < fireSize; i++) {
				int[] current = fire.poll();
				int cx = current[0];
				int cy = current[1];

				for (int dir = 0; dir < 4; dir++) {
					int nx = cx + dx[dir];
					int ny = cy + dy[dir];

					if (isInRange(nx, ny) && (map[nx][ny] == '.' || map[nx][ny] == '@')) {
						map[nx][ny] = '*';
						fire.offer(new int[] { nx, ny });
					}
				}
			}

			// 2. 사람 이동
			int meSize = me.size();
			for (int i = 0; i < meSize; i++) {
				int[] current = me.poll();
				int cx = current[0];
				int cy = current[1];

				for (int dir = 0; dir < 4; dir++) {
					int nx = cx + dx[dir];
					int ny = cy + dy[dir];

					// 사람이 탈출할 경우
					if (!isInRange(nx, ny)) {
						return time;
					}

					// 이동 가능하면 이동
					if (map[nx][ny] == '.') {
						map[nx][ny] = '@';
						me.offer(new int[] { nx, ny });
					}
				}
			}
		}

		// 탈출 실패
		return -1;
	}

	static boolean isInRange(int x, int y) {
		return 0 <= x && x < h && 0 <= y && y < w;
	}
}
