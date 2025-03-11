import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		char[] balls = new char[N];

		int redCnt = 0;
		int blueCnt = 0;

		int answer = 500001; // 0 <= n <= 500_000

		String input = br.readLine();
		for (int i = 0; i < N; i++) {
			balls[i] = input.charAt(i);
			if (balls[i] == 'R') {
				redCnt++;
			} else {
				blueCnt++;
			}
		}

		// 1. R을 왼쪽으로 모으는 경우
		int firstBallCnt = 0;
		for (int i = 0; i < N; i++) {
			if (balls[i] == 'R') {
				firstBallCnt += 1;
			} else {
				break;
			}
		}
		answer = Math.min(answer, redCnt - firstBallCnt);

		// 2. R을 오른쪽으로 모으는 경우
		firstBallCnt = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (balls[i] == 'R') {
				firstBallCnt += 1;
			} else {
				break;
			}
		}
		answer = Math.min(answer, redCnt - firstBallCnt);

		// 3. B를 왼쪽으로 모으는 경우
		firstBallCnt = 0;
		for (int i = 0; i < N; i++) {
			if (balls[i] == 'B') {
				firstBallCnt += 1;
			} else {
				break;
			}
		}
		answer = Math.min(answer, blueCnt - firstBallCnt);

		// 4. B를 오른쪽으로 모으는 경우
		firstBallCnt = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (balls[i] == 'B') {
				firstBallCnt += 1;
			} else {
				break;
			}
		}
		answer = Math.min(answer, blueCnt - firstBallCnt);

		System.out.println(answer);
	}

}
