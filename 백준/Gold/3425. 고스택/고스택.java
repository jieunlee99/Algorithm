import java.io.*;
import java.util.*;

public class Main {

	static final long MAX = 1_000_000_000; // 연산의 절댓값이 10^9를 넘지 않도록 한다.

	static List<String> commands;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {

			commands = new ArrayList<>();

			String line;

			while (true) {
				line = br.readLine();

				if (line.equals("QUIT")) {
					System.out.print(sb); // 최종 출력
					return;
				}

				if (line.equals("END"))
					break;

				String[] parts = line.split(" ");
				String command = parts[0];
				commands.add(command);

				if (command.equals("NUM")) {
					commands.add(parts[1]);
				}
			}

			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				long value = Long.parseLong(br.readLine());
				String result = executeProgram(value);
				sb.append(result).append("\n");
			}

			br.readLine(); // 한줄 건너뛰기

			sb.append("\n");
		}
	}

	static String executeProgram(long value) {
		Stack<Long> stack = new Stack<>();
		stack.push((long) value);

		for (int i = 0; i < commands.size(); i++) {
			String command = commands.get(i);

			switch (command) {

			// X를 스택의 가장 위에 저장한다.
			case "NUM":
				long num = Long.parseLong(commands.get(++i));
				stack.push(num);
				break;

			// 스택 가장 위의 숫자를 제거한다.
			case "POP":
				if (stack.isEmpty())
					return "ERROR";
				stack.pop();
				break;

			// 첫 번째 수의 부호를 바꾼다.
			case "INV":
				if (stack.isEmpty())
					return "ERROR";
				stack.push(-stack.pop());
				break;

			// 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
			case "DUP":
				if (stack.isEmpty())
					return "ERROR";
				stack.push(stack.peek());
				break;

			// 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
			case "SWP":
				if (stack.size() < 2)
					return "ERROR";
				long first = stack.pop();
				long second = stack.pop();
				stack.push(first);
				stack.push(second);
				break;

			// 첫 번째 숫자와 두 번째 숫자를 더한다.
			case "ADD":
				if (stack.size() < 2)
					return "ERROR";
				long a = stack.pop();
				long b = stack.pop();
				long sum = a + b;
				if (Math.abs(sum) > MAX)
					return "ERROR";
				stack.push(sum);
				break;

			// 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
			case "SUB":
				if (stack.size() < 2)
					return "ERROR";
				long a_sub = stack.pop();
				long b_sub = stack.pop();
				long sub = b_sub - a_sub;
				if (Math.abs(sub) > MAX)
					return "ERROR";
				stack.push(sub);
				break;

			// 첫 번째 숫자와 두 번째 숫자를 곱한다.
			case "MUL":
				if (stack.size() < 2)
					return "ERROR";
				long a_mul = stack.pop();
				long b_mul = stack.pop();
				long mul = a_mul * b_mul;
				if (Math.abs(mul) > MAX)
					return "ERROR";
				stack.push(mul);
				break;

			// 첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
			case "DIV":
				if (stack.size() < 2)
					return "ERROR";
				long a_div = stack.pop();
				long b_div = stack.pop();
				if (a_div == 0)
					return "ERROR";
				long div = Math.abs(b_div) / Math.abs(a_div);
				if (b_div < 0)
					div = -div;
				if (a_div < 0)
					div = -div;
				stack.push(div);
				break;

			// 첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
			case "MOD":
				if (stack.size() < 2)
					return "ERROR";
				long a_mod = stack.pop();
				long b_mod = stack.pop();
				if (a_mod == 0)
					return "ERROR";
				long mod = Math.abs(b_mod) % Math.abs(a_mod);
				if (b_mod < 0)
					mod = -mod;
				stack.push(mod);
				break;

			default:
				return "ERROR";
			}
		}

		// 남아있는 숫자의 개수가 한 개가 아닐 시 error
		if (stack.size() != 1)
			return "ERROR";

		return stack.pop().toString();

	}

}
