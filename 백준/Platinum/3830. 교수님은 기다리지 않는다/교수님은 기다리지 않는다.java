
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parent;
	static int[] weight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 샘플의 수
			M = Integer.parseInt(st.nextToken()); // 일의 수
			if (N == 0 && M == 0) {
				break;
			}

			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}

			weight = new int[N + 1];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				String type = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (type.equals("!")) {
					int w = Integer.parseInt(st.nextToken());
					union(a, b, w);
				} else if (type.equals("?")) {
					if (find(a) == find(b)) {
						sb.append(weight[b] - weight[a]).append("\n");
					} else {
						sb.append("UNKNOWN").append("\n");
					}
				}
			}
		}

		System.out.println(sb);

	}

	static int find(int num) {
		if (parent[num] == num) {
			return num;
		}

		int p = find(parent[num]);
		weight[num] += weight[parent[num]];
		return parent[num] = p;
	}

	static void union(int a, int b, int w) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot) {
			parent[bRoot] = aRoot;
			weight[bRoot] = weight[a] + w - weight[b];
		}
	}
}
