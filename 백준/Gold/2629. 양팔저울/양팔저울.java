import java.io.*;
import java.util.*;

public class Main {

	static final int MAX_WEIGHT = 40000;

	static int N, Q;
	static int[] weight;

	static boolean[][] dp; // dp[i][j] : i번째 추까지 사용했을 때, 무게 j를 만들 수 있는지 여부를 나타

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		weight = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		dp = new boolean[N + 1][MAX_WEIGHT + 1];
		dp[0][0] = true;

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= MAX_WEIGHT; j++) {
				if (dp[i - 1][j]) {
					// 현재 추를 사용하지 않는 경우
					dp[i][j] = true;

					// 현재 추를 더하는 경우
					if (j + weight[i] <= MAX_WEIGHT) {
						dp[i][j + weight[i]] = true;
					}

					// 현재 추를 빼는 경우
					int diff = Math.abs(j - weight[i]);
					if (diff <= MAX_WEIGHT) {
						dp[i][diff] = true;
					}
				}
			}
		}

		Q = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		while (Q-- > 0) {
			int q = Integer.parseInt(st.nextToken());
			if (q <= MAX_WEIGHT && dp[N][q]) {
				sb.append("Y ");
			} else {
				sb.append("N ");
			}
		}

		System.out.println(sb);

		br.close();
	}
}
