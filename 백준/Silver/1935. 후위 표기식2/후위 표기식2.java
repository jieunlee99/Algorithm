import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 피연산자 개수

		char[] post = br.readLine().toCharArray(); // 후위 표기식 입력

		int[] num = new int[N]; // 피연산자 값들
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}

		Stack<Double> stack = new Stack<>();

		for (int i = 0; i < post.length; i++) {
			char ch = post[i];

			if (ch >= 'A' && ch <= 'Z') {
				// A~Z일 경우, 대응되는 값 스택에 push
				stack.push((double) num[ch - 'A']);
			} else {
				// 연산자일 경우, 스택에서 두 값을 꺼내서 계산
				double b = stack.pop(); // 먼저 꺼낸 게 오른쪽 피연산자
				double a = stack.pop(); // 다음 꺼낸 게 왼쪽 피연산자

				switch (ch) {
				case '+':
					stack.push(a + b);
					break;
				case '-':
					stack.push(a - b);
					break;
				case '*':
					stack.push(a * b);
					break;
				case '/':
					stack.push(a / b);
					break;
				}
			}
		}

		// 최종 결과 출력 (소수점 둘째자리까지)
		System.out.printf("%.2f\n", stack.pop());
	}
}
