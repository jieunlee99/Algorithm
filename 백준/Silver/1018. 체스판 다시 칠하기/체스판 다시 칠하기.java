import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static boolean[][] board;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = input.charAt(j) == 'W'; // W는 true, B는 false
			}
		}

		int min = Integer.MAX_VALUE; // 가장 큰 값으로 초기화
		// 8x8 크기의 모든 영역에 대해 최소 변경 횟수 계산
		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				min = Math.min(min, cal(i, j)); // 각 8x8 영역에 대해 최소값 계산
			}
		}

		bw.write(String.valueOf(min));
		bw.flush();
		br.close();
		bw.close();
	}

	public static int cal(int x, int y) {
		int count = 0;
		boolean color = true; // 'W'가 true, 'B'가 false

		for (int i = x; i < x + 8; i++) {
			for (int j = y; j < y + 8; j++) {
				// 현재 칸의 색이 예상과 다르면 count 증가
				if (board[i][j] != color) {
					count++;
				}
				color = !color; // 다음 칸은 색이 바뀌어야 하므로 반전
			}
			color = !color; // 한 줄 끝난 후, 그 줄의 시작 색은 반전되어야 함
		}

		// 첫 번째 칸을 기준으로 할 때의 색칠 할 개수(count)와
		// 첫 번째 칸의 반대되는 색을 기준으로 할 때의 색칠 할 개수(64 - count) 중 최솟값
		return Math.min(count, 64 - count);
	}
}
