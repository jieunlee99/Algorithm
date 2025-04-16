
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static char[][] map;
	static int maxCandy = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j < N - 1) {
					swap(i, j, i, j + 1);
					maxCandy = Math.max(maxCandy, calMaxCandy());
					swap(i, j, i, j + 1);
				}

				if (i < N - 1) {
					swap(i, j, i + 1, j);
					maxCandy = Math.max(maxCandy, calMaxCandy());
					swap(i, j, i + 1, j);
				}
			}
		}

		System.out.println(maxCandy);
	}

	static int calMaxCandy() {
		int maxCandy = 0;

		for (int i = 0; i < N; i++) {
			int cntRow = 1;
			int cntCol = 1;

			for (int j = 1; j < N; j++) {
				if (map[i][j] == map[i][j - 1]) {
					cntRow++;
				} else {
					maxCandy = Math.max(maxCandy, cntRow);
					cntRow = 1;
				}

				if (map[j][i] == map[j - 1][i]) {
					cntCol++;
				} else {
					maxCandy = Math.max(maxCandy, cntCol);
					cntCol = 1;
				}
			}

			maxCandy = Math.max(maxCandy, Math.max(cntRow, cntCol));
		}

		return maxCandy;
	}

	static void swap(int x1, int y1, int x2, int y2) {
		char temp = map[x1][y1];
		map[x1][y1] = map[x2][y2];
		map[x2][y2] = temp;
	}

}
