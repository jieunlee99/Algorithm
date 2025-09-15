import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, H;
	static int[] low, high;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 동굴의 길이
		H = Integer.parseInt(st.nextToken()); // 동굴의 높이

		low = new int[H + 1];
		high = new int[H + 1];

		// 높이 입력
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			// 홀수번쨰에는 석순(아래), 짝수번쨰에는 종유석(위)
			if (i % 2 == 0) {
				low[height]++;
			} else {
				high[height]++;
			}
		}

		// 누적합 계
		for (int i = H - 1; i >= 1; i--) {
			low[i] += low[i + 1];
			high[i] += high[i + 1];
		}

		int minError = Integer.MAX_VALUE; // 개똥벌레가 파괴해야 할 장애물의 최소 개수
		int cnt = 0; // 장애물을 최소(minError)로만 만나는 경우의 수

		for (int i = 1; i <= H; i++) {
			int totalErrors = low[i] + high[H - i + 1];
			if (minError > totalErrors) {
				minError = totalErrors;
				cnt = 1;
			} else if (minError == totalErrors) {
				cnt++;
			}
		}

		System.out.println(minError + " " + cnt);
	}

}
