
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int K = 18;

	static int M, Q;
	static int[][] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		M = Integer.parseInt(br.readLine());

		parent = new int[K + 1][M + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			parent[0][i] = Integer.parseInt(st.nextToken());
		}

		initSparseTable();

		Q = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			sb.append(query(n, x)).append("\n");
		}

		System.out.println(sb);
	}

	private static int query(int n, int x) {
		int current = x;

		for (int k = 0; k <= K; k++) {
			if (n % 2 == 1) {
				current = parent[k][current];
			}
			n /= 2;
		}

		return current;
	}

	private static void initSparseTable() {
		for (int k = 1; k <= K; k++) {
			for (int v = 1; v <= M; v++) {
				parent[k][v] = parent[k - 1][parent[k - 1][v]];
			}
		}
	}
}
