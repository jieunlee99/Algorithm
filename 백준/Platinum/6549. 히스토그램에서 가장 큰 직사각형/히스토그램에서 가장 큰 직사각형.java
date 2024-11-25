import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] heights;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			if (N == 0) {
				break;
			}

			heights = new int[N];

			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}

			long maxArea = getMaxArea();
			sb.append(maxArea).append("\n");
		}

		System.out.print(sb.toString());
	}

	static long getMaxArea() {
		Stack<Integer> stack = new Stack<>();

		long maxArea = 0L;

		for (int i = 0; i <= N; i++) {
			int h = (i == N ? 0 : heights[i]);

			while (!stack.isEmpty() && heights[stack.peek()] >= h) {
				long height = heights[stack.pop()];
				long width = stack.isEmpty() ? i : (i - stack.peek() - 1);
				maxArea = Math.max(maxArea, height * width);
			}

			stack.push(i);
		}

		return maxArea;
	}
}
