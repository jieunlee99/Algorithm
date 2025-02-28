import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		int N = Integer.parseInt(br.readLine());

		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();

		for (int i = 0; i < input.length(); i++) {
			left.add(input.charAt(i));
		}

		for (int i = 0; i < N; i++) {
			String command = br.readLine();

			char c = command.charAt(0);

			switch (c) {
			case 'L':
				if(!left.isEmpty()) {
					right.push(left.pop());
				}
				break;
			case 'D':
				if(!right.isEmpty()) {
					left.push(right.pop());
				}
				break;
			case 'B':
				if(!left.isEmpty()) {
					left.pop();
				}
				break;
			case 'P':
				char t = command.charAt(2);
				left.push(t);
				break;
			default:
				break;
			}
		}
		
		while(!left.isEmpty()) {
			right.push(left.pop());
		}
		
		StringBuilder sb = new StringBuilder();
		while(!right.isEmpty()) {
			sb.append(right.pop());
		}
		
		System.out.println(sb);
	}
}
