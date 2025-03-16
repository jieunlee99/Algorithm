import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int maxArea = 0;
	static int count = 0; // 그림 개수

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1]; // 여기서 한 번만 선언

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int maxSize = 0;
		int numberOfPictures = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) { // 방문하지 않은 1을 찾으면 BFS 실행
					numberOfPictures++;
					maxSize = Math.max(maxSize, bfs(i, j));
				}
			}
		}

		System.out.println(numberOfPictures); // 총 그림 개수
		System.out.println(maxSize); // 가장 큰 그림의 크기
	}

	static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x, y });
		visited[x][y] = true;

		int area = 0; // 현재 그림의 넓이

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int cx = current[0];
			int cy = current[1];
			area++; // 방문한 칸 수 증가

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (isInRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
					queue.add(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}

		return area;
	}

	static boolean isInRange(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= M;
	}
}