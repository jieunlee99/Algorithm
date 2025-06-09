import java.io.*;
import java.util.*;

public class Main {

	static int R, C, N;
	
	static char[][] map;
	
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		if (N == 1) {
			printMap(map);
			return;
		}

		// 짝수 초는 모든 칸이 폭탄으로 채워
		if (N % 2 == 0) {
			fillBombs();
			printMap(map);
			return;
		}

		// 홀수 초마다 폭탄이 터짐
		boolean firstExplosion = true;
		for (int t = 3; t <= N; t += 2) {
			char[][] temp = copyMap(map);
			fillBombs();
			explode(temp);
			if (t == N)
				break;
		}

		printMap(map);
	}

	// 폭탄이 없는 위치에 폭탄 설치 
	static void fillBombs() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '.') {
					map[i][j] = 'O';
				}
			}
		}
	}

	// 폭탄이 설치된 위치 및 그 주변 4방향을 폭발시켜 '.'으로 만듦
	static void explode(char[][] temp) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (temp[i][j] == 'O') {
					map[i][j] = '.';
					for (int d = 0; d < 4; d++) {
						int ni = i + dx[d];
						int nj = j + dy[d];
						if (ni >= 0 && ni < R && nj >= 0 && nj < C) {
							map[ni][nj] = '.';
						}
					}
				}
			}
		}
	}

	// 깊은 복사 - 원본 훼손 방지
	static char[][] copyMap(char[][] original) {
		char[][] newMap = new char[R][C];
		for (int i = 0; i < R; i++) {
			newMap[i] = Arrays.copyOf(original[i], C);
		}
		return newMap;
	}

	static void printMap(char[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			sb.append(map[i]).append('\n');
		}
		System.out.print(sb);
	}
}
