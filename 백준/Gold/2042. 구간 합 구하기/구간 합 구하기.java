import java.io.*;
import java.util.*;

public class Main {

	static int N, S, M, K;
	static long[] arr, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수의 개수
		M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
		K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

		arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		init();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			// update (b번째 수를 c로 변경)
			if (a == 1) {
				long diff = c - arr[b - 1]; // 기존 값과의 차이
				arr[b - 1] = c; // 배열 값 갱신
				update(1, S, 1, b, diff);
			}
			// query (b번째 수부터 c번째 수까지 합 구하기)
			else if (a == 2) {
				long sum = query(1, S, 1, b, (int) c);
				sb.append(sum).append("\n");
			}
		}
		System.out.print(sb);
	}

	// 세그먼트 트리 초기화 (bottom-up)
	static void init() {
		S = 1;
		while (S < N) {
			S *= 2;
		}
		tree = new long[2 * S];

		for (int i = 0; i < N; i++) {
			tree[S + i] = arr[i];
		}

		for (int i = S - 1; i > 0; i--) {
			tree[i] = tree[2 * i] + tree[2 * i + 1];
		}
	}

	// 세그먼트 트리 update
	static void update(int left, int right, int node, int target, long diff) {
		if (target < left || target > right)
			return;

		tree[node] += diff;
		if (left != right) {
			int mid = (left + right) / 2;
			update(left, mid, node * 2, target, diff);
			update(mid + 1, right, node * 2 + 1, target, diff);
		}
	}

	// 세그먼트 트리 query
	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		if (queryRight < left || right < queryLeft)
			return 0; // 범위 밖
		if (queryLeft <= left && right <= queryRight)
			return tree[node]; // 포함됨

		int mid = (left + right) / 2;
		return query(left, mid, node * 2, queryLeft, queryRight)
				+ query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
	}
}
