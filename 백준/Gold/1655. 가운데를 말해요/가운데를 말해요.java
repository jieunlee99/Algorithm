
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // 내림차순
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2); // 오름차순

		while (N-- > 0) {
			int num = Integer.parseInt(br.readLine());

			if (maxHeap.size() == minHeap.size()) {
				maxHeap.add(num);
			} else {
				minHeap.add(num);
			}

			if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				if (maxHeap.peek() > minHeap.peek()) {
					int tmp = maxHeap.poll();
					maxHeap.offer(minHeap.poll());
					minHeap.offer(tmp);
				}
			}

			sb.append(maxHeap.peek()).append("\n");
		}

		System.out.println(sb.toString());
	}

}
