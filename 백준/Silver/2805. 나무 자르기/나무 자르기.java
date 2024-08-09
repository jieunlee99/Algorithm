import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] array;

	// parametric search vs. binary search

	// 높이를 가장 높은 나무에서 점점 내리는걸로..

	// start & mid & end가 모두 같아지면 stop

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		array = new int[N];

		int max = Integer.MIN_VALUE;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, array[i]);
		}

		int start = 0;
		int end = max;
		int mid = 0;

		int result = -1;

		while (true) {

			mid = (start + end) / 2;

			long sum = sumCutTree(mid);
			
			// mid를 날릴지 말지는 선택
			// 날릴거면 result에 저장해줘야 함.

			// sum > M : 높이 올려야 함 -> start = mid + 1, result = mid
			// sum == M : result = mid, break;
			// sum < M : 높이 내려야 함 -> end = mid-1

			if (sum > M) {
				result = mid;
				start = mid + 1;
			} else if (sum < M) {
				end = mid - 1;
			} else { // sum == M
				result = mid;
				break;
			}

			if (start > end) {
				break;
			}
		}

		System.out.println(result);
	}

	static long sumCutTree(int value) {
		long sum = 0;
		for (int a : array) {
			if (a > value) {
				sum += a - value;
			}
		}
		return sum;
	}

}
