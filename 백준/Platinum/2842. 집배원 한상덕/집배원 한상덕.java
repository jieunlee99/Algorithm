
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int houseCount;
	static int minFatigue = Integer.MAX_VALUE;
	static int startX, startY;

	static char[][] map;
	static int[][] heights;

	static ArrayList<Integer> uniqueHeights = new ArrayList<>();

	// 수직, 수평, 대각선 이동 가능
	static int[] moveX = { 1, 1, 1, 0, 0, -1, -1, -1 };
	static int[] moveY = { 1, 0, -1, 1, -1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		heights = new int[N][N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);

				if (map[i][j] == 'P') {
					startX = j;
					startY = i;
				} else if (map[i][j] == 'K') {
					houseCount++;
				}
			}
		}

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				heights[i][j] = Integer.parseInt(st.nextToken());
				set.add(heights[i][j]);
			}
		}
		uniqueHeights.addAll(set);
		Collections.sort(uniqueHeights);

		solve();
	}

	static void solve() {
		int low = 0, high = 0;

		while (low < uniqueHeights.size() && high < uniqueHeights.size()) {
			if (canReachAllHouses(uniqueHeights.get(low), uniqueHeights.get(high))) {
				minFatigue = Math.min(minFatigue, uniqueHeights.get(high) - uniqueHeights.get(low));
				low++;
			} else {
				high++;
			}
		}

		System.out.println(minFatigue);
	}

	static boolean canReachAllHouses(int minH, int maxH) {
		int startHeight = heights[startY][startX];

		if (startHeight < minH || startHeight > maxH) {
			return false;
		}

		boolean[][] visited = new boolean[N][N];

		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[] { startY, startX });
		visited[startY][startX] = true;

		int count = 0;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int y = current[0];
			int x = current[1];

			if (map[y][x] == 'K') {
				count++;
			}

			for (int d = 0; d < 8; d++) {
				int ny = y + moveY[d];
				int nx = x + moveX[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}

				if (visited[ny][nx]) {
					continue;
				}

				int height = heights[ny][nx];
				if (minH <= height && height <= maxH) {
					visited[ny][nx] = true;
					queue.add(new int[] { ny, nx });
				}
			}
		}

		return count == houseCount;
	}
}
