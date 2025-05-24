import java.io.*;
import java.util.*;

public class Main {

	static boolean[][] visited = new boolean[6][6];
	static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		List<int[]> moves = new ArrayList<>();

		for (int i = 0; i < 36; i++) {
			String current = br.readLine();
			int x = current.charAt(0) - 'A';
			int y = current.charAt(1) - '1';

			if (visited[x][y]) {
				System.out.println("Invalid");
				return;
			}

			visited[x][y] = true;
			moves.add(new int[] { x, y });
		}

		// 유효한 나이트 이동인지 확인 -L자로만 이동가능
		for (int i = 0; i < 35; i++) {
			if (!isKnightMove(moves.get(i), moves.get(i + 1))) {
				System.out.println("Invalid");
				return;
			}
		}

		// 마지막과 처음도 연결되는지 확인
		if (!isKnightMove(moves.get(35), moves.get(0))) {
			System.out.println("Invalid");
		} else {
			System.out.println("Valid");
		}
	}

	private static boolean isKnightMove(int[] from, int[] to) {
		int x1 = from[0], y1 = from[1];
		int x2 = to[0], y2 = to[1];
		for (int i = 0; i < 8; i++) {
			if (x1 + dx[i] == x2 && y1 + dy[i] == y2) {
				return true;
			}
		}
		return false;
	}
}
