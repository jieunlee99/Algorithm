import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int r, c, d;
    
	static int answer = 0;

	static int[][] map;
	static boolean[][] visited;

	static int[] dx = { -1, 0, 1, 0 }; 
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cleanRoom(r, c, d);
		System.out.println(answer);
	}

	static void cleanRoom(int x, int y, int dir) {
		// 1. 현재 위치 청소
		if (!visited[x][y]) {
			visited[x][y] = true;
			answer++;
		}

		// 2. 네 방향 탐색
		for (int i = 0; i < 4; i++) {
			dir = turnLeft(dir); // 반시계 회전
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (isInBounds(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]) {
				cleanRoom(nx, ny, dir);
				return;
			}
		}

		// 3. 네 방향 모두 청소할 곳이 없으면 후진 (뒤로 이동할 때는 현재 방향에서 180도 회전해야 함)
		int backDir = (dir + 2) % 4;
		int bx = x + dx[backDir];
		int by = y + dy[backDir];

		if (isInBounds(bx, by) && map[bx][by] != 1) {
			cleanRoom(bx, by, dir); // 후진
		}
	}

	// 왼쪽으로 90도 회전하는 함수
	static int turnLeft(int dir) {
		return (dir + 3) % 4;
	}

	// 범위 확인 함수
	static boolean isInBounds(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < M);
	}
}
