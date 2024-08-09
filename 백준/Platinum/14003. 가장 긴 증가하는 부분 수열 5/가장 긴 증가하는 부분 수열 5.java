import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// LIS

public class Main {

	static int N;
	static int[] nums;
	static int[] dp;
	static int cnt;
	
	static int[] trace;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("src/DAY10/P14003/input.txt"));
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
			if(nums[i] > dp[cnt]) {
				dp[++cnt]  = nums[i];
				trace[i] = cnt;
			} else {
				int lb = lowerbound(nums[i]);
				dp[lb] = nums[i];
				trace[i] = lb;
			}
		}

//		System.out.println(Arrays.toString(dp));
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt+1).append("\n");
		
		Stack<Integer> stack = new Stack<>();
		for(int i=N-1; i>=0; i--) {
			if(trace[i] == cnt) {
				stack.push(nums[i]);
				cnt--;
			}

		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb.toString());
	}

	static int lowerbound(int num) {
		
		int mid;
		int left = 0;
		int right = cnt;
		
		while(left < right) {
			mid = (left+right)/2;
			if(dp[mid] >= num) {
				right = mid;
			} else {
				left = mid+1;
			}
		}
		
		return right;
	}
}
