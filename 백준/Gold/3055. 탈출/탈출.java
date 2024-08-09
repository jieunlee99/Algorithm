

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static final int[] MX = { -1, 1, 0, 0 };
	static final int[] MY = { 0, 0, -1, 1 };

	static int R, C;

	static int[][] dp; // visited(boolean)
	static char[][] map; // x, y 좌표와 타입을 모두 담을 수 있음

	static boolean foundAnswer = false;

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		Queue<Point> queue = new LinkedList<>();

		R = sc.nextInt();
		C = sc.nextInt();

		map = new char[R][C];
		dp = new int[R][C];

		Point st = null;
		for (int r = 0; r < R; r++) {
			String line = sc.next();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == '*') {
					queue.add(new Point(r, c, '*'));
				} else if (map[r][c] == 'S') {
					st = new Point(r, c, 'S');
				}
			}
		}

		queue.add(st);
//		System.out.println(queue);

		while (!queue.isEmpty()) {
			// 1. 큐에서 꺼내옴 -> S, D, ., *
			Point p = queue.poll();

			// 2. 목적지인가? -> D
			if (p.type == 'D') {
				// 답 처리
				System.out.println(dp[p.y][p.x]);
				foundAnswer = true;
				break;
			}

			// 3. 연결된 곳을 순회 -> 상하좌우
			for (int i = 0; i < 4; i++) {
				int ty = p.y + MY[i];
				int tx = p.x + MX[i];

				// 4. 갈 수 있는가?
				// (공통) : 맵을 벗어나지 않고. "."
				if (0 <= ty && ty < R && 0 <= tx && tx < C) {
					if (p.type == '.' || p.type == 'S') {
						// 4. (고슴도치) : D, 방문하지 않은 곳
						if ((map[ty][tx] == '.' || map[ty][tx] == 'D') && dp[ty][tx] == 0) {
							// 5. 체크인: dp에 거리 입력
							dp[ty][tx] = dp[p.y][p.x] + 1;
							// 6. 큐에 넣음
							queue.add(new Point(ty, tx, map[ty][tx]));
						}

					} else {
						// 4. (물) : S -> 이미 고슴도치 지나간데라 신경쓰지 않아도 될수도
						if (map[ty][tx] == '.' || map[ty][tx] == 'S') {
							// 5. 체크인: map에 기록
							map[ty][tx] = '*';
							// 6. 큐에 넣음
							queue.add(new Point(ty, tx, '*'));
						}

					}
				}
			}

		}
		if (foundAnswer == false) {
			System.out.println("KAKTUS");
		}

	}
}

class Point {
	int y;
	int x;
	char type;

	public Point(int y, int x, char type) {
		this.y = y;
		this.x = x;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + ", type=" + type + "]";
	}

}