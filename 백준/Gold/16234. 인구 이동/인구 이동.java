import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };

	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static List<int[]> union;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int day = 0;

		while (true) {
			boolean isMoved = false;
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						int totalPopulation = bfs(i, j);

						if (union.size() > 1) {
							movePopulation(totalPopulation);
							isMoved = true;
						}
					}
				}
			}

			if (!isMoved)
				break;

			day++;
		}

		System.out.println(day);
	}

	private static void movePopulation(int totalPopulation) {
		int avg = totalPopulation / union.size();
		for (int[] pos : union) {
			map[pos[0]][pos[1]] = avg;
		}
	}

	private static int bfs(int x, int y) {

		Queue<int[]> queue = new LinkedList<>();
		union = new ArrayList<>();

		queue.offer(new int[] { x, y });
		visited[x][y] = true;
		union.add(new int[] { x, y });

		int sum = map[x][y];

		while (!queue.isEmpty()) {
			int[] current = queue.poll();

			int cx = current[0];
			int cy = current[1];

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
					continue;
				}

				int diff = Math.abs(map[nx][ny] - map[cx][cy]);

				if (diff >= L && diff <= R) {
					// 연결된 나라끼리 인구 수 엔빵해야 함
					queue.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
					union.add(new int[] { nx, ny });
					sum += map[nx][ny];
				}
			}
		}

		return sum;
	}
}
