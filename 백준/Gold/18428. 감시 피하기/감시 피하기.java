import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static char[][] map;

	static boolean found = false;

	static List<int[]> teachers = new ArrayList<>();
	static List<int[]> empties = new ArrayList<>();

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == 'T') {
					teachers.add(new int[] { i, j });
				} else if (map[i][j] == 'X') {
					empties.add(new int[] { i, j });
				}
			}
		}

		// x 중 3개를 조합으로 뽑아서 장애물 설치
		comb(0, 0, new int[3][]);

		System.out.println(found ? "YES" : "NO");
	}

	// 알고리즘 - 백트래킹
	// depth: 현재 선택 수, start: 다음 시작 인덱스, selected: 선택된 좌표들
	private static void comb(int depth, int start, int[][] selected) {

		if (found) {
			return;
		}

		if (depth == 3) {
			// 3개를 골랐다면 장애물을 설치함
			for (int[] pos : selected) {
				map[pos[0]][pos[1]] = 'O';
			}

			if (isSafe()) {
				found = true;
			}

			// 원상 복구
			for (int[] pos : selected) {
				map[pos[0]][pos[1]] = 'X';
			}

			return;
		}

		for (int i = start; i < empties.size(); i++) {
			selected[depth] = empties.get(i); // 현재 위치 선택
			comb(depth + 1, i + 1, selected); // 다음 위치 선택
		}

	}

	private static boolean isSafe() {
		for (int[] t : teachers) {
			int x = t[0];
			int y = t[1];

			// 네 방향 감시
			for (int i = 0; i < 4; i++) {
				int nx = x;
				int ny = y;

				// 현재 방향으로 계속 이동하면서 감시
				while (true) {
					nx += dx[i];
					ny += dy[i];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						break;

					if (map[nx][ny] == 'O')
						break;

					if (map[nx][ny] == 'S')
						return false;
				}
			}
		}
		return true;
	}

}
