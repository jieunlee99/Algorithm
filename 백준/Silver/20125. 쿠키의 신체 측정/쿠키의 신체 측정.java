import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		char[][] board = new char[N][N];

		int center = 0;
		int left_arm = 0, right_arm = 0;
		int left_leg = 0, right_leg = 0;

		Point head = new Point(-1, -1);
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
			if (head.x == -1) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '*') {
						head.x = i + 2;
						head.y = j + 1;
					}
				}
			}
		}
		System.out.println(head.x + " " + head.y);

		for (int i = 0; i < N; i++) {
			if (board[head.x - 1][i] == '*') {
				if (i < head.y - 1) {
					left_arm++;
				} else if (i > head.y - 1) {
					right_arm++;
				}
			}
		}

		for (int i = head.x; i < N; i++) {
			if (board[i][head.y - 1] == '*') {
				center++;
			}

			if (board[i][head.y - 1 - 1] == '*') {
				left_leg++;
			}

			if (board[i][head.y - 1 + 1] == '*') {
				right_leg++;
			}
		}

		System.out.println(left_arm + " " + right_arm + " " + center + " " + left_leg + " " + right_leg);
	}

}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
