import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] arr;

	static int cnt_1 = 0;
	static int cnt0 = 0;
	static int cnt1 = 0;

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
		sb.append(cnt_1).append("\n");
		sb.append(cnt0).append("\n");
		sb.append(cnt1).append("\n");
		System.out.print(sb.toString());
	}

	static void divide(int x, int y, int size) {
		if (checkColor(x, y, size)) {
			if (arr[x][y] == -1) {
				cnt_1++;
			} else if (arr[x][y] == 0) {
				cnt0++;
			} else if (arr[x][y] == 1) {
				cnt1++;
			}
			return;
		}

		int newSize = size / 3;

		divide(x, y, newSize);
		divide(x + newSize, y, newSize);
		divide(x + newSize * 2, y, newSize);
		divide(x, y + newSize, newSize);
		divide(x + newSize, y + newSize, newSize);
		divide(x + newSize * 2, y + newSize, newSize);
		divide(x, y + newSize * 2, newSize);
		divide(x + newSize, y + newSize * 2, newSize);
		divide(x + newSize * 2, y + newSize * 2, newSize);
	}

	static boolean checkColor(int x, int y, int size) {
		int color = arr[x][y];

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (arr[i][j] != color) {
					return false;
				}
			}
		}

		return true;
	}

}
