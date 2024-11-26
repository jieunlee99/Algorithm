import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] nums;
	static List<Integer> dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		nums = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		dp = new ArrayList<>();

		for (int num : nums) {
			int pos = binarySearch(num);

			if (pos >= dp.size()) {
				dp.add(num);
			} else {
				dp.set(pos, num);
			}
		}

		System.out.println(dp.size());
	}

	static int binarySearch(int num) {
		int low = 0, high = dp.size() - 1;

		while (low <= high) {
			int mid = (low + high) / 2;

			if (dp.get(mid) < num) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return low;
	}
}
