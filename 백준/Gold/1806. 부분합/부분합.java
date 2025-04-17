
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int answer = Integer.MAX_VALUE;

		int sum = 0;
		int low = 0, high = 0;

		while (high < N) {
			sum += nums[high++];

			while (sum >= S) {
				answer = Math.min(answer, high - low);
				sum -= nums[low++];
			}
		}

		System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
	}
}
