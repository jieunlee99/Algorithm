
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, S;
	static int[] A;
	static int[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		A = new int[N+1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		initTree();

		M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());

			if (type == 1) {
				// A[i]를 v로 변경
				int i = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				update(i, v);
			} else if (type == 2) {
				// 최소값 출력
				sb.append(tree[1]).append("\n");
			}
		}

		System.out.print(sb);
	}

	private static void update(int target, int value) {
		A[target] = value;
		
		int node = S + target - 1;

		node /= 2;

		while (node > 0) {
			tree[node] = getMinIndex(tree[2 * node], tree[2 * node + 1]);
			node /= 2;
		}
	}

	private static int getMinIndex(int idx1, int idx2) {
		if (idx1 == 0)
			return idx2;
		if (idx2 == 0)
			return idx1;

		if (A[idx1] < A[idx2])
			return idx1;
		if (A[idx1] > A[idx2])
			return idx2;

		return Math.min(idx1, idx2);
	}

	private static void initTree() {
		S = 1;
		while (S < N) {
			S *= 2;
		}

		tree = new int[2 * S];

		for (int i = 1; i <= N; i++) {
			tree[S + i - 1] = i;
		}

		for (int i = S - 1; i > 0; i--) {
			tree[i] = getMinIndex(tree[2 * i], tree[2 * i + 1]);
		}
	}
}
