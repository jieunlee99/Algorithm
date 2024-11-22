import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] arr;
	static int whiteCount = 0;
	static int blueCount = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		divide(0, 0, N);

		StringBuilder sb = new StringBuilder();
		sb.append(whiteCount).append("\n");
		sb.append(blueCount).append("\n");
		System.out.print(sb.toString());
	}

	static void divide(int x, int y, int size) {
		if (checkColor(x, y, size)) {
			if (arr[x][y] == 0) {
				whiteCount++;
			} else {
				blueCount++;
			}
			return;
		}

		int newSize = size / 2;

		divide(x, y, newSize);
		divide(x, y + newSize, newSize);
		divide(x + newSize, y, newSize);
		divide(x + newSize, y + newSize, newSize);
	}

	static boolean checkColor(int x, int y, int size) {
		int color = arr[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				// 영역 안에 시작점과 다른 색이 있다면 false;
				if (arr[i][j] != color) {
					return false;
				}
			}
		}
		return true;
	}
}
