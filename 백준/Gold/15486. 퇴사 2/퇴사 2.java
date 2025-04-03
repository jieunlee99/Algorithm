
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] time = new int[N];
		int[] price = new int[N];

		int[] dp = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			if (i + time[i] <= N) {
				dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + price[i]);
			}
			dp[i + 1] = Math.max(dp[i + 1], dp[i]);
		}

		System.out.println(dp[N]);
	}

}
