import java.io.*;
import java.util.*;

public class Main {

	static class Document {
		int index, priority;

		public Document(int index, int priority) {
			this.index = index;
			this.priority = priority;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			Queue<Document> queue = new LinkedList<>();

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int[] priorities = new int[10]; // 우선순위 카운트용 (1~9)

			for (int i = 0; i < N; i++) {
				int priority = Integer.parseInt(st.nextToken());
				queue.offer(new Document(i, priority));
				priorities[priority]++;
			}

			int count = 0;

			while (!queue.isEmpty()) {
				Document current = queue.poll();

				// 현재 문서보다 더 높은 우선순위가 있는지 확인
				boolean hasHigherPriority = false;
				for (int i = current.priority + 1; i <= 9; i++) {
					if (priorities[i] > 0) {
						hasHigherPriority = true;
						break;
					}
				}

				if (hasHigherPriority) {
					// 우선순위 높은 게 있으므로 뒤로 보냄
					queue.offer(current);
				} else {
					// 출력 처리
					count++;
					priorities[current.priority]--;

					if (current.index == M) {
						sb.append(count).append("\n");
						break;
					}
				}
			}
		}

		System.out.print(sb);
	}
}
