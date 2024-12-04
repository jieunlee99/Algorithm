import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] time;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 출발
		K = Integer.parseInt(st.nextToken()); // 도착

		// N == K인 경우 바로 0 출력
		if (N == K) {
			System.out.println(0);
			return;
		}

		time = new int[100_001]; // 0부터 100000까지 처리
		Arrays.fill(time, Integer.MAX_VALUE);

		Deque<Integer> deque = new ArrayDeque<>();

		deque.add(N);
		time[N] = 0;

		while (!deque.isEmpty()) {
			int current = deque.poll();

			// 순간 이동 (2 * current)
			if (2 * current <= 100_000 && time[2 * current] > time[current]) {
				time[2 * current] = time[current]; // 시간 추가 없음
				deque.addFirst(2 * current); // 우선 처리
			}

			// 앞으로 이동 (current + 1)
			if (current + 1 <= 100_000 && time[current + 1] > time[current] + 1) {
				time[current + 1] = time[current] + 1;
				deque.addLast(current + 1);
			}

			// 뒤로 이동 (current - 1)
			if (current - 1 >= 0 && time[current - 1] > time[current] + 1) {
				time[current - 1] = time[current] + 1;
				deque.addLast(current - 1);
			}
		}

		System.out.println(time[K]);
	}
}
