import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 부모 배열 초기화
		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}

		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (op == 0) {
				union(a, b);
			} else if (op == 1) {
				if (find(a) == find(b)) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			}
		}

		System.out.print(sb.toString());
	}

	// find 함수: 경로 압축 적용
	static int find(int num) {
		if (parent[num] != num) {
			parent[num] = find(parent[num]); // 경로 압축
		}
		return parent[num];
	}

	// union 함수: 두 원소를 합치는 연산
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot) {
			parent[aRoot] = bRoot;
		}
	}
}
