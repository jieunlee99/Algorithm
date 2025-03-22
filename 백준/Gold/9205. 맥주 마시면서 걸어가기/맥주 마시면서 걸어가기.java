import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		ArrayList<Point> pos;
		ArrayList<ArrayList<Integer>> graph;

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			pos = new ArrayList<>();

			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				pos.add(new Point(x, y));
			}

			graph = new ArrayList<>();
			for (int i = 0; i < N + 2; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < N + 2; i++) {
				for (int j = i + 1; j < N + 2; j++) {
					// 맥주 20개를 가지고 다니고, 50m 당 한 병을 마신다 => 한 번에 갈 수 있는 최대 거리가 1000m이다.
					if (Manhattan(pos.get(i), pos.get(j)) <= 1000) {
						// adjList
						graph.get(i).add(j);
						graph.get(j).add(i);
					}
				}
			}

			sb.append(bfs(graph, N) ? "happy" : "sad").append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	static boolean bfs(ArrayList<ArrayList<Integer>> graph, int N) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 2];

		queue.offer(0);
		visited[0] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			if (current == N + 1) {
				return true;
			}

			for (int next : graph.get(current)) {
				if (!visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}

		return false;
	}

	static int Manhattan(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}
