import java.io.*;
import java.util.*;

public class Main {

	static final int MAX = 100_000;
	static int N, K;
	static boolean[] visited = new boolean[MAX + 1];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 시작
		K = Integer.parseInt(st.nextToken()); // 끝

		int minTime = Integer.MAX_VALUE;

		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[] { N, 0 });

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int pos = current[0];
			int cnt = current[1];
			visited[pos] = true;

			if (pos == K) {
				minTime = Math.min(minTime, cnt);
			}

			int[] nextArr = { pos + 1, pos - 1, pos * 2 };

			for (int next : nextArr) {
				if (isInRange(next)&&!visited[next]) {
					queue.add(new int[] { next, cnt + 1 });
				}
			}
		}

		System.out.println(minTime);
	}

	static boolean isInRange(int num) {
		return 0 <= num && num <= MAX;
	}
}
