
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		int answer = 0;

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (find(a) == find(b)) {
				answer = i;
				break;
			}

			union(a, b);

		}

		System.out.println(answer);

	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot) {
			parent[aRoot] = bRoot;
		}
	}

	static int find(int num) {
		if (parent[num] == num) {
			return num;
		}
		return parent[num] = find(parent[num]);
	}
}
