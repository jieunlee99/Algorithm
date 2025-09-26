
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 1_000_000;

	static int[] arr = new int[100]; // 입력 수 저장
	static int[] factor = new int[MAX + 1]; // 소인수별 지수 합 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			countFactor(arr[i]); // 입력된 수를 소인수분해해서 factor 누적
		}

		int score = 1; // 문제에서 구하는 특별한 GCD
		int move = 0; // 연산 횟수

		// 소인수별 평균 지수 기반으로 GCD 만들기
		for (int i = 2; i <= MAX; i++) {
			int repeat = factor[i] / N; // 평균 지수
			
			if (repeat > 0) {
				// GCD에 i^repeat 포함
				for (int j = 0; j < repeat; j++) {
					score *= i;
				}

				// move 계산: 각 수가 가진 i의 지수와 repeat 비교
				for (int j = 0; j < N; j++) {
					int cnt = 0, tmp = arr[j];
					while (tmp % i == 0) {
						cnt++;
						tmp /= i;
					}
					if (cnt < repeat) {
						move += (repeat - cnt);
					}
				}
			}
		}

		System.out.println(score + " " + move);
	}

	// 소인수분해: 각 소수의 지수를 factor 배열에 누적
	static void countFactor(int n) {
		for (int i = 2; i * i <= n; i++) {
			while (n % i == 0) {
				n /= i;
				factor[i]++;
			}
		}
		if (n > 1) {
			factor[n]++;
		}
	}
}
