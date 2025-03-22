import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] time = new int[N];
		int[] price = new int[N];

		int[] dp = new int[N + 1]; // x일째 최대 비용 저장

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			if (i + time[i] <= N) { // 오늘날짜 + 걸리는시간 더했을 때 범위 안에 있는지 확인
				// 여태 계산했을 받을 수 있는 비용이랑 지금 이 상담 추가했을 때 비용을 비교
				dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + price[i]);
			}
			// 다음 dp = 현재 누적값과 다음 누적값 중에 큰
			dp[i + 1] = Math.max(dp[i + 1], dp[i]);
		}

		System.out.println(dp[N]);
	}

}
