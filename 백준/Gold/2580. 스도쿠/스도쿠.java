import java.io.*;
import java.util.*;

public class Main {

	static int[][] sudoku = new int[9][9];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		backtracking(0, 0); 
	}

	static void backtracking(int row, int col) {
		if (col == 9) {
			backtracking(row + 1, 0);
			return;
		}

		// 2. 목적지인가?
		if (row == 9) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(sudoku[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.print(sb);
			System.exit(0);
		}

		// 빈 칸이 있다면 1~9까지 넣어봄
		if (sudoku[row][col] == 0) {
			// 3. 순회
			for (int i = 1; i <= 9; i++) {
				// 4. 갈 수 있는가
				if (isValid(row, col, i)) {
					// 5. 간다
					sudoku[row][col] = i;
					backtracking(row, col + 1);
				}
			}

			// 6. 체크아웃
			sudoku[row][col] = 0;
			return;
		}

		// 해당 열에 빈 칸이 없을 때
		backtracking(row, col + 1);
	}

	static boolean isValid(int row, int col, int value) {

		// 같은 행, 열 검사
		for (int i = 0; i < 9; i++) {
			if (sudoku[row][i] == value || sudoku[i][col] == value) {
				return false;
			}
		}

		// 3*3 검사 (0, 3, 6으로 만들기)
		int tx = (row / 3) * 3;
		int ty = (col / 3) * 3;

		for (int i = tx; i < tx + 3; i++) {
			for (int j = ty; j < ty + 3; j++) {
				if (sudoku[i][j] == value) {
					return false;
				}
			}
		}

		return true;
	}

}
