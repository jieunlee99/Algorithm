import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, S;
	static int[] tree, result;

	static int A, B, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		// 사탕의 맛은 1부터 1_000_000까지 표현 가능함
		S = 1;
		while (S <= 1_000_000) {
			S *= 2;
		}
		tree = new int[2 * S];

		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			// query -> update -> 출력
			if (A == 1) {
				int flavor = query(B); // B번쨰 맛
				sb.append(flavor).append("\n");
				update(flavor, -1);
			}

			// update (사탕 +/-)
			else if (A == 2) {
				C = Integer.parseInt(st.nextToken());
				update(B, C);
			}
		}

		System.out.println(sb);
	}

	// bottom-up

	static void update(int index, int diff) {
		index += S - 1;
		tree[index] += diff;
		while (index > 1) { // root 노드까지 연쇄적으로 업데이트 해줘야 함
			index /= 2;
			tree[index] = tree[index * 2] + tree[index * 2 + 1];
		}
	}

	static int query(long k) {
		int node = 1;
		while (node < S) {
			// k보다 작으면 왼쪽 자식 순회
			if (tree[node * 2] >= k) {
				node *= 2;
			}

			// k보다 크면 왼쪽 자식 값만큼 빼준 뒤 오른쪽 자식 순
			else {
				k -= tree[node * 2];
				node = node * 2 + 1;
			}
		}
		return node - (S - 1);
	}
}
