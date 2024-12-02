import java.io.*;
import java.util.*;

public class Main {

	static int T, I;
	static boolean[][] visited;
	static final int[] dx = { 2, 2, -2, -2, 1, 1, -1, -1 };
	static final int[] dy = { 1, -1, 1, -1, 2, -2, 2, -2 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {

			I = Integer.parseInt(br.readLine());

			visited = new boolean[I][I];

			StringTokenizer st = new StringTokenizer(br.readLine());
			int fromX = Integer.parseInt(st.nextToken());
			int fromY = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int toX = Integer.parseInt(st.nextToken());
			int toY = Integer.parseInt(st.nextToken());

			Pos from = new Pos(fromX, fromY);
			Pos to = new Pos(toX, toY);

			sb.append(bfs(from, to)).append("\n");

		}

		System.out.println(sb.toString());
	}

	static int bfs(Pos from, Pos to) {

		int cnt = 0;

		Queue<Pos> queue = new LinkedList<>();

		visited[from.x][from.y] = true;
		queue.add(from);

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int s = 0; s < size; s++) {
				Pos current = queue.poll();

				if (current.x == to.x && current.y == to.y) {
					return cnt;
				}

				for (int i = 0; i < 8; i++) {
					Pos next = new Pos(current.x + dx[i], current.y + dy[i]);
					if (isInRange(next) && !visited[next.x][next.y]) {
						visited[next.x][next.y] = true;
						queue.add(next);
					}
				}

			}

			cnt++;
		}

		return -1;
	}

	static boolean isInRange(Pos pos) {
		return 0 <= pos.x && pos.x < I && 0 <= pos.y && pos.y < I;
	}
}

class Pos {
	int x, y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}