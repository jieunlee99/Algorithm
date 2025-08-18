import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 나무의 수
		int M = Integer.parseInt(st.nextToken()); // 집으로 가져가려고 하는 나무의 길이

		int[] trees = new int[N];

		int low = 0, high = 0;

		// ex) 20, 15, 10, 17
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			high = Math.max(high, trees[i]);
		}

		// 적어도 M미터의 나무를 집에 가져가기 위해 절단기에 설정할 수 있는 높이의 최대값 탐색 - 이진 탐색
		while (low < high) {
			int mid = (low + high) / 2;

			long sum = sumCutTree(trees, mid);

			if (sum < M) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}

		// upperbound 방식의 이진 탐색이기 때문에 반환된 값에 -1을 해줘야 한다.
		System.out.println(low - 1);
	}

	static long sumCutTree(int[] trees, int value) {
		long sum = 0L;
		for (int tree : trees) {
			if (tree > value) {
				sum += (tree - value);
			}
		}
		return sum;
	}
}
