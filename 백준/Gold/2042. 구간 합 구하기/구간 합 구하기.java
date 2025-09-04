// bottom up

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
				update(b, c);
			}
			// query (b번째 수부터 c번째 수까지 합 구하기)
			else if (a == 2) {
				long sum = query(b, (int) c);
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
	static void update(int target, long value) {
		int node = S + target - 1;
		tree[node] = value;

		node /= 2;

		while (node > 0) {
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
			node /= 2;
		}
	}

	// 세그먼트 트리 query
	static long query(int queryLeft, int queryRight) {
		int left = S + queryLeft - 1;
		int right = S + queryRight - 1;

		long sum = 0;

		while (left <= right) {
			if (left % 2 == 1) {
				sum += tree[left++];
			}

			if (right % 2 == 0) {
				sum += tree[right--];
			}

			left /= 2;
			right /= 2;
		}

		return sum;
	}
}
