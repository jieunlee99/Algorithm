import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Deque<Integer> deque = new ArrayDeque<Integer>();

		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();

			if (cmd.equals("push_front")) {
				int x = Integer.parseInt(st.nextToken());
				deque.addFirst(x);
			} else if (cmd.equals("push_back")) {
				int x = Integer.parseInt(st.nextToken());
				deque.addLast(x);
			} else if (cmd.equals("pop_front")) {
				if (deque.isEmpty()) {
					sb.append(-1).append("\n");
					continue;
				}
				sb.append(deque.removeFirst()).append("\n");
			} else if (cmd.equals("pop_back")) {
				if (deque.isEmpty()) {
					sb.append(-1).append("\n");
					continue;
				}
				sb.append(deque.removeLast()).append("\n");
			} else if (cmd.equals("size")) {
				sb.append(deque.size()).append("\n");
			} else if (cmd.equals("empty")) {
				sb.append(deque.isEmpty() ? 1 : 0).append("\n");
			} else if (cmd.equals("front")) {
				if (deque.isEmpty()) {
					sb.append(-1).append("\n");
					continue;
				}
				sb.append(deque.peekFirst()).append("\n");
			} else if (cmd.equals("back")) {
				if (deque.isEmpty()) {
					sb.append(-1).append("\n");
					continue;
				}
				sb.append(deque.peekLast()).append("\n");
			}
		}

		System.out.print(sb);
	}
}
