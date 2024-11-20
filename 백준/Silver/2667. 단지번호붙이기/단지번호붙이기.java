import java.io.*;
import java.util.*;

public class Main {

	static int N;

	static int[][] map;
	static boolean[][] visited;
	static List<Integer> houseNumList;

	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		houseNumList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					int houseNum = bfs(i, j);
					houseNumList.add(houseNum);
				}
			}
		}
		
		Collections.sort(houseNumList);
		
		StringBuilder sb = new StringBuilder();
		sb.append(houseNumList.size()).append("\n");
		for(int h:houseNumList) {
			sb.append(h).append("\n");
		}

		System.out.print(sb.toString());
	}

	static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { x, y });
		visited[x][y] = true;
		
		int cnt = 1;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int cx = current[0];
			int cy = current[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(isInRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
					queue.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
					cnt++;
				}
			}
		}
 
		return cnt;
	}

	static boolean isInRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
