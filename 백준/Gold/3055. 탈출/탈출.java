import java.io.*;
import java.util.*;

public class Main {

	static int R, C;

	static char[][] map;
	static int[][] dp;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static boolean foundAnswer = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		dp = new int[R][C];

		Queue<Point> queue = new LinkedList<>();

		Point start = null;

		for (int r = 0; r < R; r++) {
			String input = br.readLine();

			for (int c = 0; c < C; c++) {
				map[r][c] = input.charAt(c);

				if (map[r][c] == '*') {
					queue.offer(new Point(r, c, '*'));
				}

				else if (map[r][c] == 'S') {
					start = new Point(r, c, 'S');
				}
			}
		}

		// 물이 차있는 지역을 먼저 큐에 채우고 시작점을 넣어준다.
		queue.offer(start);

		while (!queue.isEmpty()) {
			// 1. 큐에서 꺼냄
			Point p = queue.poll();

			// 2. 목적지인가?
			if (p.type == 'D') {
				System.out.println(dp[p.x][p.y]);
				foundAnswer = true;
				break;
			}

			// 3. 순회
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				// 4. 갈 수 있는가?
				if (isInRange(nx, ny)) {

					// 현재 큐에 세 타입이 담겨져 있음

					if (p.type == '.' || p.type == 'S') {
						if ((map[nx][ny] == '.' || map[nx][ny] == 'D') && dp[nx][ny] == 0) {
							// 5. 간다.
							dp[nx][ny] = dp[p.x][p.y] + 1;
							queue.add(new Point(nx, ny, map[nx][ny]));
						}
					}

					else { // p.type == '*' -> 다음 칸에 물이 차게 됨
						if (map[nx][ny] == '.' || map[nx][ny] == 'S') {
							map[nx][ny] = '*';
							queue.add(new Point(nx, ny, '*'));
						}
					}
				}
			}
		}

		if (!foundAnswer) {
			System.out.println("KAKTUS");
		}
	}

	static boolean isInRange(int x, int y) {
		return 0 <= x && x < R && 0 <= y && y < C;
	}

	static class Point {
		int x, y;
		char type;

		public Point(int x, int y, char type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
}
