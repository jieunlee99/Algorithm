
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack<Character> stack = new Stack<>();

		String input = br.readLine();

		int result = 0; // 최종 결과
		int tmp = 1; // 현재 괄호 깊이에 따른 곱셈 값 (중첩 구조 계산용)

		boolean isValid = true; // 올바른 괄호열인지 체크

		for (int i = 0; i < input.length(); i++) {

			char c = input.charAt(i);

			// 여는 소괄호 '(' → 스택에 넣고 곱셈 값 2배
			if (c == '(') {
				stack.push('(');
				tmp *= 2;
			}

			// 여는 대괄호 '[' → 스택에 넣고 곱셈 값 3배
			else if (c == '[') {
				stack.push('[');
				tmp *= 3;
			}

			// 닫는 소괄호 ')'
			else if (c == ')') {

				// 스택이 비었거나, 짝이 맞지 않는 경우 → 올바른 괄호열 아님
				if (stack.isEmpty() || stack.peek() != '(') {
					isValid = false;
					break;
				}

				// 직전 문자가 '('이면 → "()" 구조이므로 tmp 값을 결과에 더함
				if (input.charAt(i - 1) == '(') {
					result += tmp;
				}

				// 짝 맞는 '(' 제거, 곱셈 값 원상복귀 (2로 나눔)
				stack.pop();
				tmp /= 2;
			}

			// 닫는 대괄호 ']'
			else if (c == ']') {

				// 스택이 비었거나, 짝이 맞지 않는 경우 → 올바른 괄호열 아님
				if (stack.isEmpty() || stack.peek() != '[') {
					isValid = false;
					break;
				}

				// 직전 문자가 '['이면 → "[]" 구조이므로 tmp 값을 결과에 더함
				if (input.charAt(i - 1) == '[') {
					result += tmp;
				}

				// 짝 맞는 '[' 제거, 곱셈 값 원상복귀 (3으로 나눔)
				stack.pop();
				tmp /= 3;
			}
		}

		// 모든 검사가 끝났을 때
		// 1) isValid == true (짝이 잘 맞음)
		// 2) stack.isEmpty() == true (모든 괄호 다 닫힘)
		// 둘 다 만족해야 정답, 아니면 0
		System.out.println(isValid && stack.isEmpty() ? result : 0);
	}

}
