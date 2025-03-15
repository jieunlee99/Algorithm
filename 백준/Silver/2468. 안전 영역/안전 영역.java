
import java.util.*;
import java.io.*;

public class Main {

	static int N;

	static int[][] map;
	static boolean[][] visited;

	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		
		int maxHeight = 0;
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		
		int maxSafeZones = 1; // 최소 1개는 확보함
	
		for(int rain=0; rain<=maxHeight; rain++) {
			visited = new boolean[N + 1][N + 1];
			int cnt = 0;
			
			for(int i=1; i<=N;i++) {
				for(int j=1; j<=N; j++) {
					if(!visited[i][j] && map[i][j] > rain) {
						dfs(i, j, rain);
						cnt++;
					}
				}
			}
			
			maxSafeZones = Math.max(maxSafeZones, cnt);
		}

		System.out.println(maxSafeZones);
	}

	static void dfs(int x, int y, int rain) {
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (isInRange(nx, ny) && map[nx][ny] > rain && !visited[nx][ny]) {
				dfs(nx, ny, rain);
			}
		}
	}

	static boolean isInRange(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= N;
	}
}
