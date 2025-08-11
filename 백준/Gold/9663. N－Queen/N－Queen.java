import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int cnt;
	static int[] board; // board[row] = col → 각 행(row)에 퀸이 놓인 열(col)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		board = new int[N];

		backtracking(0);

		System.out.println(cnt);
	}

	static void backtracking(int depth) {

		// 목적지인가
		if (depth == N) {
			cnt++;
			return;
		}

		// 순회
		for (int i = 0; i < N; i++) {
			board[depth] = i;

			// 갈 수 있는가
			if (isPossible(depth)) {
				// 간다
				backtracking(depth + 1);
			}
		}
	}

	static boolean isPossible(int col) {
		for (int i = 0; i < col; i++) {

			// 같은 열에 있는지
			if (board[i] == board[col]) {
				return false;
			}

			// 대각선에 있는지
			else if (Math.abs(col - i) == Math.abs(board[col] - board[i])) {
				return false;
			}
		}

		return true;
	}

}
