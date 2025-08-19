import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// -- 슬라이딩 윈도우 방식으로 직전 줄만 기억하는 최적화 필요

		// 현재 줄까지의 최대, 최소 dp 값 저장
		int[] minDp = new int[3];
		int[] maxDp = new int[3];

		// 새로운 줄을 계산할 때 임시 저장
		int[] tempMin = new int[3];
		int[] tempMax = new int[3];

		// -- 입력 받으면서 dp 수행

		// - 첫 번째 줄 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			int value = Integer.parseInt(st.nextToken());
			minDp[i] = value;
			maxDp[i] = value;
		}

		// - 두 번째 줄부터 N번째 줄 입력 받고 dp 수행
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 3; j++) {
				int value = Integer.parseInt(st.nextToken());

				// 최소값 계산
				if (j == 0) {
					tempMin[j] = Math.min(minDp[0], minDp[1]) + value;
				} else if (j == 1) {
					tempMin[j] = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]) + value;
				} else { // j==2
					tempMin[j] = Math.min(minDp[1], minDp[2]) + value;
				}

				// 최대값 계산
				if (j == 0) {
					tempMax[j] = Math.max(maxDp[0], maxDp[1]) + value;
				} else if (j == 1) {
					tempMax[j] = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]) + value;
				} else { // j==2
					tempMax[j] = Math.max(maxDp[1], maxDp[2]) + value;
				}
			}

			// 각 줄에서 계산된 최소, 최대값을 dp에 저장한 후 다음 줄 수행
			System.arraycopy(tempMax, 0, maxDp, 0, 3);
			System.arraycopy(tempMin, 0, minDp, 0, 3);
		}

		int minResult = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);
		int maxResult = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);

		// -- 결과값 출력
		System.out.println(maxResult + " " + minResult);
	}

}
