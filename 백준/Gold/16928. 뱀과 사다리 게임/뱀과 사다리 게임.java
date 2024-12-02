import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사다리의 수
		M = Integer.parseInt(st.nextToken()); // 뱀의 수

		Map<Integer, Integer> map = new HashMap<>();
		for (int n = 0; n < N + M; n++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map.put(from, to);
		}

		int cnt = 0;

		Queue<Integer> queue = new LinkedList<>();
		visited = new boolean[101];

		int start = 1;
		queue.add(start);

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int s = 0; s < size; s++) {
				int current = queue.poll();

				if (current == 100) {
					System.out.println(cnt);
					return;
				}

				for (int i = 1; i <= 6; i++) {
					int next = current + i;

					if (next > 100 || visited[next]) {
						continue;
					}

					if (map.containsKey(next)) {
						next = map.get(next);
					}

					if (!visited[next]) {
						visited[next] = true;
						queue.add(next);
					}
				}
			}

			cnt++;
		}

		System.out.println(cnt);
	}

}
