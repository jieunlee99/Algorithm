
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 초기 n+1개의 집합: {0}, {1}, ..., {n}
		M = Integer.parseInt(st.nextToken()); // 연산의 개수

		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 합집합
			if (type == 0) {
				union(a, b);
			}

			// a와 b가 같은 집합에 속하는지 확인 - YES/NO
			else if (type == 1) {
				if (find(a) == find(b)) {
					sb.append("YES").append("\n");
				} else {
					sb.append("NO").append("\n");
				}
			}
		}

		System.out.println(sb);
	}

	// 합집합 수행
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot) {
			parent[aRoot] = bRoot;
		}
	}

	// 해당 원소의 root를 찾아줌
	static int find(int num) {
		if (parent[num] != num) {
			parent[num] = find(parent[num]); // 경로 압축
		}
		return parent[num];
	}

}
