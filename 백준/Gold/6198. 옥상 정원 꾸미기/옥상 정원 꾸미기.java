import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static long sum = 0L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());

			while (!stack.isEmpty() && stack.peek() <= height) {
				stack.pop();
			}

			stack.push(height);
			sum += stack.size() - 1;
		}

		System.out.println(sum);
	}

}
