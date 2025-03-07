import java.io.*;
import java.util.*;

public class Main {

	static int n, m;

	static int[][] map;
	static boolean[][] visited;
	static int[][] result;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];
		result = new int[n][m];

		int x = 0, y = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					x = i;
					y = j;
				} else if (map[i][j] == 0) {
					visited[i][j] = true;
				}
			}
		}

		bfs(x, y);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) {
					result[i][j] = -1;
				}
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x, y });
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];
				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (!visited[nx][ny] && map[nx][ny] == 1) {
						visited[nx][ny] = true;
						result[nx][ny] = result[current[0]][current[1]] + 1;
						queue.add(new int[] { nx, ny });
					}
				}
			}
		}
	}

}
