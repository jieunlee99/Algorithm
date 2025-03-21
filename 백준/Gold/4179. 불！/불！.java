import java.io.*;
import java.util.*;

public class Main {

	static int R, C;

	static char[][] map;
	static Queue<Point> jihoon, fire; 

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		jihoon = new LinkedList<>();
		fire = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'J') {
					jihoon.add(new Point(i, j));
				} else if (map[i][j] == 'F') {
					fire.add(new Point(i, j));
				}
			}
		}

		int time = 0;

		
		// 불 확산 후 지훈 이동
		while (true) {
			time++;

			int fireSize = fire.size();
			for (int i = 0; i < fireSize; i++) {
				Point temp = fire.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nx = temp.x + dx[dir];
					int ny = temp.y + dy[dir];

					if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
						continue;
					}

					if (map[nx][ny] == 'J' || map[nx][ny] == '.') {
						map[nx][ny] = 'F';
						fire.offer(new Point(nx, ny));
					}
				}
			}

			int jihoonSize = jihoon.size();
			for (int i = 0; i < jihoonSize; i++) {
				Point temp = jihoon.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nx = temp.x + dx[dir];
					int ny = temp.y + dy[dir];

					if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
						System.out.println(time);
						return;
					}

					if (map[nx][ny] == '.') {
						map[nx][ny] = 'J';
						jihoon.offer(new Point(nx, ny));
					}
				}
			}
			
			if (jihoon.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				return;
			}
		}

	}
}
