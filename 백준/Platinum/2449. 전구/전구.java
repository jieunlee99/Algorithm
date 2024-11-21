import java.util.*;
import java.io.*;

public class Main {

	static int N, K;
	static int[] colors;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 전구의 수
		K = Integer.parseInt(st.nextToken()); // 색의 수

		colors = new int[N + 1];
		dp = new int[N + 1][N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
		}

		int result = func(1, N);
		System.out.println(result);
	}

	static int func(int left, int right) {
		// 전구가 한개만 있으면 바꿀 필요 x
		if (left == right) {
			return 0;
		}

		// 이미 구한 dp값 존재
		if (dp[left][right] != 0) {
			return dp[left][right];
		}

		int tmp = 0;
		dp[left][right] = Integer.MAX_VALUE;
		// 분할 정복
		for (int i = left; i < right; i++) {
			// 양쪽 구간의 색이 같으면 0,  다르면 1
			tmp = colors[left] != colors[i + 1] ? 1 : 0;
			dp[left][right] = Math.min(dp[left][right], func(left, i) + func(i + 1, right) + tmp);
		}

		return dp[left][right];
	}
}
