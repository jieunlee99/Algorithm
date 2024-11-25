import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] heights = new int[N + 1];

		Stack<int[]> stack = new Stack<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());

			// 왼쪽에서 오는 더 높은 탑을 찾음
			while (!stack.isEmpty() && stack.peek()[1] < heights[i]) {
				stack.pop();
			}

			// 더 높은 탑이 없다면 0 출력
			if (stack.isEmpty()) {
				sb.append("0 ");
			} else {
				sb.append(stack.peek()[0]).append(" ");
			}

			// 현재 탑을 스택에 추가
			stack.push(new int[] { i, heights[i] });
		}

		System.out.println(sb.toString());
	}
}
