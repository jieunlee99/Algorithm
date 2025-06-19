
import java.io.*;
import java.util.*;

public class Main {

	static char[][] map;
	static boolean[][] visited;

	static int R, C, K;

	static int cnt = 0;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}

		dfs(R - 1, 0, 1);

		System.out.println(cnt);

	}

	static void dfs(int x, int y, int depth) {

		// 1. 체크인
		visited[x][y] = true;

		// 2. 목적지인가
		if (depth == K) {
			if (x == 0 && y == C - 1) {
				cnt++;
			}
			visited[x][y] = false;
			return;
		}

		// 3. 순회
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			// 4. 갈 수 있는가
			if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == '.') {
				// 5. 간다
				dfs(nx, ny, depth + 1);
			}
		}

		// 6. 체크 아웃
		visited[x][y] = false;

	}

	static boolean isInRange(int x, int y) {
		return 0 <= x && x < R && 0 <= y && y < C;
	}

}
