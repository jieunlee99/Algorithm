

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parent;
	static int[] weight;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		StringTokenizer st;

		String q;
		int a, b, w;
		int aRoot, bRoot;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 샘플의 개수
			M = Integer.parseInt(st.nextToken()); // 실험실에서 한 일의 수

			if (N == 0 && M == 0) {
				break;
			}

			parent = new int[N + 1];
			weight = new int[N + 1];

			// initailize()
			for (int n = 1; n <= N; n++) {
				parent[n] = n;
			}

			for (int m = 0; m < M; m++) {

				st = new StringTokenizer(br.readLine());

				q = st.nextToken();
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				aRoot = find(a);
				bRoot = find(b);

				if (q.equals("!")) {
					w = Integer.parseInt(st.nextToken());

					// ! a b w => b가 a보다 w그램 무겁다.

					// union
					// root보다 얼마나 무거운지를 노드에 저장한다.

					if (aRoot != bRoot) {
						parent[bRoot] = aRoot;
						weight[bRoot] = weight[a] + w - weight[b];
					}

				} else if (q.equals("?")) {
					// ? a b => b가 a보다 얼마나 무거운지 출력하라
					if (aRoot == bRoot) {
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
		if (parent[num] == num)
			return num;
		else {
			int p = find(parent[num]);
			weight[num] += weight[parent[num]];
			return parent[num] = p;
		}
//			return parent[num] = find(parent[num]);
	}

}
