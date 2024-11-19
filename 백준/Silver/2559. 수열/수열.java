import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		nums[1] = Integer.parseInt(st.nextToken());
		for (int i = 2; i <= N; i++) {
			nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());
		}
				
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<N-K+1; i++) {
			int temp = nums[i+K]-nums[i];
			max = Math.max(max, temp);
		}

		System.out.println(max);
	}

}
