import java.io.*;
import java.util.*;

public class Main {

	static int M, N, K;
	static int[][] map;
	static boolean[][] visited;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new boolean[M][N];

		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			// 직사각형 채우기 (열린 구간 처리)
			for (int i = b; i < d; i++) {
				for (int j = a; j < c; j++) {
					map[i][j] = 1;
				}
			}
		}

		int cnt = 0;
		ArrayList<Integer> areas = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					areas.add(bfs(i, j));
					cnt++;
				}
			}
		}

		Collections.sort(areas);

		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for (int area : areas) {
			sb.append(area).append(" ");
		}

		System.out.println(sb);
	}

	static int bfs(int x, int y) {
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<>();
		visited[x][y] = true;
		queue.add(new int[] { x, y });

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			cnt++;

			for (int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];

				if (isInRange(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new int[] { nx, ny });
				}
			}
		}
		return cnt;
	}

	static boolean isInRange(int x, int y) {
		return 0 <= x && x < M && 0 <= y && y < N;
	}
}