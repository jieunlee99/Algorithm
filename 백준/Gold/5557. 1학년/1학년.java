import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N; // 입력받는 전체 숫자의 개수 (N-1개의 숫자와 1개의 결과 값)

	static int[] num; // N-1개의 연산 대상 숫자를 저장하는 배열
	static int result; // 연산을 통해 최종적으로 얻어져야 하는 계산 값 (마지막 숫자)

	// DP 테이블: dp[i][j]는 i번째 숫자까지 연산했을 때, 합이 j가 되는 경우의 수
	// i: 0부터 N-2까지 (연산 대상 숫자의 인덱스)
	// j: 0부터 20까지 (중간 합의 범위)
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		// N-1개의 숫자를 담을 배열 초기화
		num = new int[N - 1];
		// DP 테이블 초기화 (최대 N-1 x 21 크기)
		dp = new long[N - 1][21];

		StringTokenizer st = new StringTokenizer(br.readLine());

		// N-1번째 숫자까지 연산 대상 숫자를 저장
		for (int i = 0; i < N - 1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		// 마지막 숫자는 최종 목표 계산 값(결과)
		result = Integer.parseInt(st.nextToken());

		// 1. 초기값 셋팅
		// 첫 번째 숫자(num[0])까지 연산을 수행했을 때,
		// 합이 num[0]이 되는 경우의 수는 1개이다.
		dp[0][num[0]] = 1;

		// 2. DP 업데이트
		// 두 번째 숫자(i=1)부터 마지막 연산 대상 숫자(N-2)까지 반복
		for (int i = 1; i < N - 1; i++) {
			// 이전 단계(i-1)에서 나올 수 있는 모든 합(j)에 대해 반복 (0부터 20까지)
			for (int j = 0; j <= 20; j++) {
				// 이전 단계에서 합이 j가 되는 경우의 수가 1개라도 있다면 (dp[i-1][j] > 0)
				if (dp[i - 1][j] > 0) {

					// 현재 숫자(num[i])를 더한 경우의 합
					int plus = j + num[i];
					// 현재 숫자(num[i])를 뺀 경우의 합
					int minus = j - num[i];

					// 연산 중간 나오는 수는 모두 0 이상 20 이하이어야 한다는 조건 확인

					// 덧셈 연산 결과가 20 이하이면, 새로운 경우의 수를 업데이트
					if (plus <= 20) {
						// dp[i][plus] = (i-1번째까지 합이 j였던 경우의 수)
						// + (새롭게 i번째까지 합이 plus가 된 경우의 수)
						dp[i][plus] += dp[i - 1][j];
					}

					// 뺄셈 연산 결과가 0 이상이면, 새로운 경우의 수를 업데이트
					if (minus >= 0) {
						// dp[i][minus] = (i-1번째까지 합이 j였던 경우의 수)
						// + (새롭게 i번째까지 합이 minus가 된 경우의 수)
						dp[i][minus] += dp[i - 1][j];
					}
				}
			}
		}

		// 최종 결과 출력:
		// N-2번째 숫자까지 연산했을 때, 최종 목표 값(result)이 되는 경우의 수를 출력
		System.out.println(dp[N - 2][result]);
	}
}
