import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static final int MAX = 100_000;
	static boolean[] visited = new boolean[MAX + 1];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 시작
		K = Integer.parseInt(st.nextToken()); // 도착

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(N);
		visited[N] = true;

		int time = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int pos = queue.poll();

				if (pos == K) {
					return time;
				}

				int[] nextArr = { pos - 1, pos + 1, pos * 2 };
				for (int next : nextArr) {
					if (0 <= next && next <= MAX && !visited[next]) {
						visited[next] = true;
						queue.add(next);
					}
				}
			}
			time++;
		}

		return -1;
	}
}
