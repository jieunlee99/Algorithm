import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, S;
	static int[] arr, treeMin, treeMax;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		S = 1;
		while (S < N) {
			S *= 2;
		}
		treeMin = new int[2 * S];
		treeMax = new int[2 * S];

		Arrays.fill(treeMin, Integer.MAX_VALUE);
		Arrays.fill(treeMax, 0);

		initTree();

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			sb.append(queryMin(left, right)).append(" ").append(queryMax(left, right)).append("\n");
		}

		System.out.print(sb);
	}

	private static int queryMin(int queryLeft, int queryRight) {
		int min = Integer.MAX_VALUE;

		int left = S + queryLeft - 1;
		int right = S + queryRight - 1;

		while (left <= right) {
			if (left % 2 == 1) {
				min = Math.min(min, treeMin[left++]);
			}

			if (right % 2 == 0) {
				min = Math.min(min, treeMin[right--]);
			}

			left /= 2;
			right /= 2;
		}

		return min;
	}

	private static int queryMax(int queryLeft, int queryRight) {
		int max = 0;

		int left = S + queryLeft - 1;
		int right = S + queryRight - 1;

		while (left <= right) {
			if (left % 2 == 1) {
				max = Math.max(max, treeMax[left++]);
			}

			if (right % 2 == 0) {
				max = Math.max(max, treeMax[right--]);
			}

			left /= 2;
			right /= 2;
		}

		return max;
	}

	private static void initTree() {
		for (int i = 1; i <= N; i++) {
			treeMin[S + i - 1] = arr[i];
			treeMax[S + i - 1] = arr[i];
		}

		for (int i = S - 1; i > 0; i--) {
			treeMin[i] = Math.min(treeMin[2 * i], treeMin[2 * i + 1]);
			treeMax[i] = Math.max(treeMax[2 * i], treeMax[2 * i + 1]);
		}
	}
}
