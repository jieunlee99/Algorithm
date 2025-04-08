import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			char[] cmd = br.readLine().toCharArray();
			int n = Integer.parseInt(br.readLine());

			String input = br.readLine();
			input = input.substring(1, input.length() - 1); // 괄호 제거

			Deque<Integer> deque = new ArrayDeque<>();
			if (n > 0) {
				StringTokenizer st = new StringTokenizer(input, ",");
				while (st.hasMoreTokens()) {
					deque.offer(Integer.parseInt(st.nextToken()));
				}
			}

			boolean reversed = false;
			boolean error = false;

			for (char c : cmd) {
				if (c == 'R') {
					reversed = !reversed;
				} else if (c == 'D') {
					if (deque.isEmpty()) {
						sb.append("error\n");
						error = true;
						break;
					}
					if (reversed) {
						deque.pollLast();
					} else {
						deque.pollFirst();
					}
				}
			}

			if (!error) {
				sb.append("[");
				while (!deque.isEmpty()) {
					sb.append(reversed ? deque.pollLast() : deque.pollFirst());
					if (!deque.isEmpty())
						sb.append(",");
				}
				sb.append("]\n");
			}
		}

		System.out.print(sb);
	}
}
