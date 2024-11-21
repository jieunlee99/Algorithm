import java.io.*;
import java.util.*;

public class Main {

	static int T, N;
	static int[] cards;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());

			cards = new int[N + 1];
			dp = new int[N + 1][N + 1]; // dp[i][j]: i~j번째까지 게임을 했을 때 최대값

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}

			// DP 테이블 초기화
			for (int i = 0; i <= N; i++) {
				Arrays.fill(dp[i], -1);
			}

			System.out.println(solution(1, N));
		}
	}

	static int solution(int left, int right) {
		if (left > right) {
			return 0;
		}

		// 이미 계산된 값이 있는 경우 반환
		if (dp[left][right] != -1) {
			return dp[left][right];
		}

		// 내 차례일 때 가능한 최적 선택 계산
		int pickLeft = cards[left] + Math.min(solution(left + 2, right), solution(left + 1, right - 1));
		int pickRight = cards[right] + Math.min(solution(left + 1, right - 1), solution(left, right - 2));

		// dp[left][right]는 내가 해당 구간에서 얻을 수 있는 최대 점수
		dp[left][right] = Math.max(pickLeft, pickRight);

		return dp[left][right];
	}
}
