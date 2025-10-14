import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;

	static int[] time;
	static int[] indegree;
	static int[] answer;
	static List<Integer>[] adjList;
	static Queue<Integer> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		time = new int[N + 1];
		indegree = new int[N + 1];
		answer = new int[N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		queue = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());

			while (true) {
				int num = Integer.parseInt(st.nextToken());
				if (num == -1) {
					break;
				}

				adjList[num].add(i); // num 건물을 지은 후에 i 건물을 지어야 함
				indegree[i]++;
			}
		}

		// 진입차수가 0인 건물을 큐에 넣어줌
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				answer[i] = time[i]; // 무조건 자기 건물을 짓는 시간은 포함하게 됨
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int next : adjList[current]) {
				indegree[next]--;
				answer[next] = Math.max(answer[next], answer[current] + time[next]);

				if (indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(answer[i]).append("\n");
		}
		System.out.print(sb);
	}

}
