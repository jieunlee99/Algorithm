import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N + 1][M + 1];

		// 현재 블랙인지, 화이트인지
		// 홀수 행은 블랙부터 시작
		boolean isWhite = false;
		for (int i = 1; i <= N; i++) {
			String input = br.readLine();
			for (int j = 1; j <= M; j++) {
				char c = input.charAt(j - 1);

				// 블랙 차례에 화이트가 오거나, 화이트 차례에 블랙이 오면 1 추가
				if ((!isWhite && c == 'W') || (isWhite && c == 'B')) {
					board[i][j] = 1;
				}
				// 다음 확인 위해서 변경
				isWhite = !isWhite;
			}

			if (M % 2 == 0) { // 짝수 행은 W부터 시작
				isWhite = !isWhite;
			}
		}

		// 누적합 : 행 더하기
		for (int i = 1; i <= N; i++) {
			int temp = board[i][1];
			for (int j = 2; j <= M; j++) {
				temp += board[i][j];
				board[i][j] = temp;
			}
		}

		// 누적합 : 열 더하기
		for (int i = 1; i <= M; i++) {
			int temp = board[1][i];
			for (int j = 2; j <= N; j++) {
				temp += board[j][i];
				board[j][i] = temp;
			}
		}

		// 탐색
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = K; i <= N; i++) {
			for (int j = K; j <= M; j++) {
				int temp = board[i][j] - (board[i - K][j] + board[i][j - K] - board[i - K][j - K]);
				min = Math.min(min, temp);
				max = Math.max(max, temp);
			}
		}

		int answer = Math.min(K * K - max, min);
		System.out.println(answer);

	}
}
