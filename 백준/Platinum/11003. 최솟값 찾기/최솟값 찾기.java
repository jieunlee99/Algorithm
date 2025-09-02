import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		Deque<Node> deque = new LinkedList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			//  맨 뒤와 비교하여 그 값보다 작으면 원래꺼를 삭제함
			while (!deque.isEmpty() && deque.peekLast().num > num) {
				deque.pollLast();
			}

			// 현재 값 추가
			deque.add(new Node(i, num));

			// 슬라이딩 윈도우 크기보다 오래 살아있었으면 삭제함
			while (!deque.isEmpty() && deque.peekFirst().index < i - L + 1) {
				deque.pollFirst();
			}

			// 현재의 최소값 출력 (저장)
			sb.append(deque.peekFirst().num).append(" ");
		}

		System.out.println(sb);
	}

	static class Node {
		int index, num;

		public Node(int index, int num) {
			this.index = index;
			this.num = num;
		}
	}
}
