import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		A = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(A);

		int cnt = 0;

		for (int i = 0; i < N; i++) {
			if (isGood(i))
				cnt++;
		}

		System.out.println(cnt);
	}

	// 두 수를 더해서 A[idx]를 만들 수 있는지 확인하는 함수
	public static boolean isGood(int idx) {
		long target = A[idx]; // 찾는 값
		
		int left = 0;
		int right = N - 1;

		while (left < right) {
			long sum = A[left] + A[right];

			if (sum == target) {
				if (left != idx && right != idx) {
					return true;
				} else if (left == idx) {
					left++;
				} else if (right == idx) {
					right--;
				}
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}

		return false;
	}

}
