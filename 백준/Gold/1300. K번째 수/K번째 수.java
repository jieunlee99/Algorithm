import java.io.*;
import java.util.*;

public class Main {

	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		int result = 0;

		int low = 1;
		int high = K;

		while (low <= high) {
			int mid = (low + high) / 2;

			if (countLessOrEqual(mid) >= K) {
				result = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		System.out.println(result);
	}

	static int countLessOrEqual(int num) {
		int count = 0;
		for (int i = 1; i <= N; i++) {
			count += Math.min(num / i, N); // 해당 행에서 num 이하의 수 개수만큼 더해준다.
		}
		return count;
	}
}
