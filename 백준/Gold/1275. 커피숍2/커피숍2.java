import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, S, Q;
	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		S = 1;
		while (S < N) {
			S *= 2;
		}
		tree = new long[2 * S];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			update(i, num);
		}

		while (Q-- > 0) {
		
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (x > y) {
				int temp = x;
				x = y;
				y = temp;
			}

			long sum = query(x, y);
			sb.append(sum).append("\n");

			update(a, b);
		}

		System.out.println(sb);
	}
 
	static void update(int index, long newValue) {
		index += S - 1;
		tree[index] = newValue;
		while (index > 1) {
			index /= 2;
			tree[index] = tree[index * 2] + tree[index * 2 + 1];
		}
	}

	static long query(int left, int right) {
		long sum = 0L;

		left += S - 1;
		right += S - 1;

		while (left <= right) {
			if (left % 2 == 1) {
				sum += tree[left];
				left++;
			}

			if (right % 2 == 0) {
				sum += tree[right];
				right--;
			}

			left /= 2;
			right /= 2;
		}

		return sum;
	}
}
