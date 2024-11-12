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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		dp = new int[N];
		trace = new int[N];

		// 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

        // LIS
        
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

		bw.write(cnt + 1 + "\n");

		Stack<Integer> stack = new Stack<>();
		for (int i = N - 1; i >= 0; i--) {
			if (trace[i] == cnt) {
				stack.push(nums[i]);
				cnt--;
			}
		}

		while (!stack.isEmpty()) {
			bw.write(stack.pop() + " ");
		}
		bw.write("\n");

		bw.flush();

		bw.close();
		br.close();
	}

	// binary search

	static int lowerbound(int num) {
		int mid;
		int left = 0, right = cnt;

		while (left < right) {
			mid = (left + right) / 2;
			if (dp[mid] >= num) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return right;
	}
}
