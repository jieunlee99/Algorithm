
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] heights;
	static int[][] dp;

	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 입력받기
		N = Integer.parseInt(br.readLine());
		heights = new int[N];
		dp = new int[N][(N / 2) + 2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());

			// 불가능한 경우 거르기
			if (heights[i] > Math.min(i, (N - 1) - i)) {
				System.out.println(0);
				return;
			}
		}

		// 2. DP 초기값 설정
		dp[0][0] = 1;

		// 3. DP 실행
		for (int i = 1; i < N; i++) {
			int maxHeight = Math.min(i, (N - 1) - i);

			if (heights[i] == -1) {
				// 높이를 모르는 상태일 때, 모든 가능한 높이에 대해 탐색
				for (int j = 0; j <= maxHeight; j++) {
					updateDP(i, j);
				}
			} else {
				// 높이를 아는 상태일 때, 주어진 높이에 대해서만 탐색
				updateDP(i, heights[i]);
			}
		}

		// 4. 정답 출력 (마지막 열에서 높이 0인 경우의 수)
		System.out.println(dp[N - 1][0] % MOD);
	}

	// DP 업데이트 함수
	private static void updateDP(int currentIndex, int currentHeight) {
		// 인접 열의 높이 차이를 고려하여 DP 배열 업데이트
		for (int k = -1; k <= 1; k++) {
			int adjHeight = currentHeight + k;
			if (adjHeight < 0)
				continue;
			dp[currentIndex][currentHeight] += dp[currentIndex - 1][adjHeight];
			dp[currentIndex][currentHeight] %= MOD;
		}
	}
}
