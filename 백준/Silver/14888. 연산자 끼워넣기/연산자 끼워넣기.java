import java.io.*;
import java.util.*;

public class Main {

	static int N;

	static int[] num;
	static int[] op = new int[4];

	static int maxValue = Integer.MIN_VALUE;
	static int minValue = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		num = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		backtracking(1, num[0]);

		StringBuilder sb = new StringBuilder();
		sb.append(maxValue).append("\n");
		sb.append(minValue).append("\n");

		System.out.print(sb);
	}

	static void backtracking(int depth, int value) {
		if (depth == N) {
			maxValue = Math.max(maxValue, value);
			minValue = Math.min(minValue, value);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] > 0) {
				op[i]--;

				if (i == 0) {
					backtracking(depth + 1, value + num[depth]);
				} else if (i == 1) {
					backtracking(depth + 1, value - num[depth]);
				} else if (i == 2) {
					backtracking(depth + 1, value * num[depth]);
				} else if (i == 3) {
					backtracking(depth + 1, value / num[depth]);
				}

				op[i]++;
			}
		}
	}

}
