import java.io.*;
import java.util.*;

public class Main {

	static int N;

	static char[][] map;
	static boolean[][] visited;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		int first = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, map[i][j]);
					first++;
				}
			}
		}

		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'R') {
					map[i][j] = 'G';
				}
			}
		}

		int second = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, map[i][j]);
					second++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(first).append(" ");
		sb.append(second).append("\n");

		System.out.print(sb);
	}

	static void bfs(int x, int y, char c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x, y });
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];

				if (isInRange(nx, ny) && map[nx][ny] == c && !visited[nx][ny]) {
					queue.add(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}

	}

	static boolean isInRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
