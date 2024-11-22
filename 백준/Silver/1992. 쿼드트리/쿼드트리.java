import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = input.charAt(j) - '0';
			}
		}

		divide(0, 0, N);

		System.out.println(sb.toString());
	}

	static void divide(int x, int y, int size) {
		if (checkColor(x, y, size)) {
			if(arr[x][y] == 1) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			return;
		}
		
		sb.append("(");

		int newSize = size / 2;
		divide(x, y, newSize);
		divide(x, y + newSize, newSize);
		divide(x + newSize, y, newSize);
		divide(x + newSize, y + newSize, newSize);

		sb.append(")");
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
