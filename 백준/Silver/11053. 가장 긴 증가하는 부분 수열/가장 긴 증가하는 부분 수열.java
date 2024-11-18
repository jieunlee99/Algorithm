
import java.util.*;
import java.io.*;

public class Main {

	static int N;

	static int[] nums;
	static int[] dp;
	static int[] trace;

	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		dp = new int[N];
		trace = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		cnt = 0;

		dp[0] = nums[0];

		for (int i = 0; i < N; i++) {
			if (nums[i] > dp[cnt]) {
				dp[++cnt] = nums[i];
				trace[i] = cnt;
			} else {
				int lb = lowerbound(nums[i]);
				dp[lb] = nums[i];
				trace[i] = lb;
			}
		}

		System.out.print(cnt + 1);
	}

	static int lowerbound(int n) {
		int left = 0, right = cnt;
		int mid;

		while (left < right) {
			mid = (left + right) / 2;

			if (dp[mid] >= n) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return right;
	}

}
