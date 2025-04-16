
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();

		Stack<Character> stack = new Stack<>();
		
		int result = 0; // 괄호로 만들어진 숫자 더하기 (x)
		int tmp = 1; // 괄호 완성될때 곱하기
		
		boolean isValid = true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (c == '(') {
				stack.push('(');
				tmp *= 2;
			} else if (c == '[') {
				stack.push('[');
				tmp *= 3;
			} else if (c == ')') {
				// 괄호 짝이 안맞으면 invalid
				if (stack.isEmpty() || stack.peek() != '(') {
					isValid = false;
					break;
				}
				if (input.charAt(i - 1) == '(') {
					result += tmp;
				}
				stack.pop();
				tmp /= 2;
			} else if (c == ']') {
				if (stack.isEmpty() || stack.peek() != '[') {
					isValid = false;
					break;
				}
				if (input.charAt(i - 1) == '[') {
					result += tmp;
				}
				stack.pop();
				tmp /= 3;
			}
		}

		if (!isValid || !stack.isEmpty()) {
			System.out.println(0);
		} else {
			System.out.println(result);
		}
	}
}
