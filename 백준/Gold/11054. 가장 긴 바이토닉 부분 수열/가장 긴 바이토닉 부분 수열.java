import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] nums, lis, lds;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		lis = new int[N]; // 앞부터 탐색
		lds = new int[N]; // 뒤부터 탐색

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// LIS 계산
		for (int i = 0; i < N; i++) {
			lis[i] = 1; // 최소 길이
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
				}
			}
		}

		// LDS 계산
		for (int i = N - 1; i >= 0; i--) {
			lds[i] = 1;
			for (int j = N - 1; j >= 0; j--) {
				if (nums[j] < nums[i] && lds[i] < lds[j] + 1) {
					lds[i] = lds[j] + 1;
				}
			}
		}
		
		int maxLength = 0;
		for(int i=0; i<N; i++) {
			maxLength = Math.max(maxLength, lis[i]+lds[i]-1);
		}
		
		System.out.println(maxLength);
	}
}
