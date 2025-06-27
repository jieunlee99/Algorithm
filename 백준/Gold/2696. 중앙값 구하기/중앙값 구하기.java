import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int M = Integer.parseInt(br.readLine());

			// 최대 힙에는 중앙값보다 작은 수들을 저장하고, 최소 힙에는 중앙값보다 큰 수들을 저장한다.
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();

			// 중앙값의 개수 출력
			sb.append(M / 2 + 1).append("\n");

			// 한 줄에 10개씩 출력하기 위해 카운트
			int cnt = 0;

			for (int i = 0; i < M; i++) {
				// 10개씩 입력 받기 위해서
				if (i % 10 == 0) {
					st = new StringTokenizer(br.readLine());
				}

				int x = Integer.parseInt(st.nextToken());

				// 두 힙의 크기가 같으면 maxHeap에 삽입 -> 중앙값은 항상 maxHeap에 존재
				if (maxHeap.size() == minHeap.size()) {
					maxHeap.offer(x);
				} else {
					minHeap.offer(x);
				}

				// 두 힙의 top을 비교해서 maxHeap의 top이 항상 작거나 같도록 유지 -> 그렇지 않으면 swap
				if (!minHeap.isEmpty()) {
					if (maxHeap.peek() > minHeap.peek()) {
						int t1 = maxHeap.poll();
						int t2 = minHeap.poll();

						maxHeap.offer(t2);
						minHeap.offer(t1);
					}
				}

				// i가 짝수일 때마다 중앙값 출력
				if (i % 2 == 0) {
					if (cnt == 9 || i == M - 1) {
						sb.append(maxHeap.peek() + "\n");
						cnt = 0;
					} else {
						sb.append(maxHeap.peek() + " ");
					}
					cnt++;
				}
			}

		}

		System.out.println(sb);

	}

}
