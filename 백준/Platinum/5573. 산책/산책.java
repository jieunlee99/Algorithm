import java.io.*;
import java.util.*;

public class Main {

	static int H, W, N;

	static int[][] map;
	static long[][] dp; // (i, j) 칸에 N번째 사람이 도착하기 전, 이미 몇 명의 사람(N-1명)이 이 칸을 지나갔는지 저장함

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken()); // 추적할 사람의 인덱스

		map = new int[H][W];
		dp = new long[H + 1][W + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 모든 사람이 (0,0)에서 시작하기 때문에 N-1명이 이 칸을 지나감
		dp[0][0] = N - 1;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				// 1. dp[i][j]가 짝수라면 두 방향으로 나누어 분배
				dp[i + 1][j] += dp[i][j] >> 1;
				dp[i][j + 1] += dp[i][j] >> 1;

				// 2. dp[i][j]가 홀수라면

				// 2-1. 최종 방향이 1(오)일 경우: 아래쪽으로 이동
				if ((map[i][j] + dp[i][j]) % 2 == 1) {
					dp[i + 1][j] += (dp[i][j] % 2);
				}

				// 2-2. 최종 방향이 0(아)일 경우: 오른쪽으로 이동
				else {
					dp[i][j + 1] += (dp[i][j] % 2);

				}
			}
		}

		// dp를 통해 최종 경로 추적
		int dh = 0, dw = 0;

		// 가장 오른쪽이나 아래쪽에 도달하면 끝
		while (dh < H && dw < W) {
			// 아래쪽으로 이동
			if ((map[dh][dw] + dp[dh][dw]) % 2 == 0) {
				dh++;
			}

			// 오른쪽으로 이동
			else {
				dw++;
			}
		}

		// 1-based 인덱스이므로 1을 더해 출력
		System.out.println((dh + 1) + " " + (dw + 1));
	}

}
