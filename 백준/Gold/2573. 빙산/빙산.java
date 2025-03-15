
import java.io.*;
import java.util.*;

class Iceberg {
	int x, y;

	Iceberg(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N, M;
	
	static int[][] map;
	
	static final int[] dx = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static final int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 입력 처리
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int years = 0;

		while (true) {
			int icebergCount = countIcebergs();
			if (icebergCount >= 2) { // 빙산이 2개 이상으로 분리된 경우
				break;
			}
			if (icebergCount == 0) { // 빙산이 다 녹은 경우
				years = 0;
				break;
			}

			meltIcebergs(); // 빙산 녹이기
			years++;
		}

		bw.write(years + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	/** 현재 빙산 덩어리 개수를 구하는 함수 */
	public static int countIcebergs() {
		boolean[][] visited = new boolean[N][M];
		int count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					dfs(i, j, visited);
					count++;
				}
			}
		}
		return count;
	}

	/** DFS를 이용해 빙산 덩어리를 탐색하는 함수 */
	public static void dfs(int x, int y, boolean[][] visited) {
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
				if (map[nx][ny] > 0 && !visited[nx][ny]) {
					dfs(nx, ny, visited);
				}
			}
		}
	}

	/** 빙산을 녹이는 함수 */
	public static void meltIcebergs() {
		int[][] tempMap = new int[N][M]; // 녹은 후의 빙산 상태를 저장할 배열

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					int seaCount = countAdjacentSea(i, j);
					tempMap[i][j] = Math.max(0, map[i][j] - seaCount);
				}
			}
		}

		// 녹은 후의 결과를 원래 배열에 적용
		for (int i = 0; i < N; i++) {
			System.arraycopy(tempMap[i], 0, map[i], 0, M);
		}
	}

	/** 현재 위치(x, y) 주변의 바다(0)의 개수를 반환하는 함수 */
	public static int countAdjacentSea(int x, int y) {
		int seaCount = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
				if (map[nx][ny] == 0) {
					seaCount++;
				}
			}
		}
		return seaCount;
	}
}
