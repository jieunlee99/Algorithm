import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, x, y, K;
	static int[][] map;

	// 1(동), 2(서), 3(북), 4(남)
	static final int[] dx = { 0, 0, 0, -1, 1 };
	static final int[] dy = { 0, 1, -1, 0, 0 };

	// 처음에는 주사위 모든 면이 0으로 설정되어 있음
	static int[] dice = new int[6];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken());

			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
				continue;
			}

			rollDice(dir);
			x = nx;
			y = ny;

			// 지도의 칸이 0이면, 주사위 바닥면의 값이 지도로 복사됨
			if (map[x][y] == 0) {
				map[x][y] = dice[1];
			}

			// 지도의 칸이 0이 아니면, 지도의 값이 주사위 바닥면으로 복사되고 지도는 0이 됨
			else {
				dice[1] = map[x][y];
				map[x][y] = 0;
			}

			sb.append(dice[0]).append("\n");
		}

		System.out.print(sb);
	}

	static void rollDice(int dir) {
		int temp = dice[0]; // 윗면 저장

		if (dir == 1) { // 동
			dice[0] = dice[4];
			dice[4] = dice[1];
			dice[1] = dice[5];
			dice[5] = temp;
		} else if (dir == 2) { // 서
			dice[0] = dice[5];
			dice[5] = dice[1];
			dice[1] = dice[4];
			dice[4] = temp;
		} else if (dir == 3) { // 북
			dice[0] = dice[2];
			dice[2] = dice[1];
			dice[1] = dice[3];
			dice[3] = temp;
		} else if (dir == 4) { // 남
			dice[0] = dice[3];
			dice[3] = dice[1];
			dice[1] = dice[2];
			dice[2] = temp;
		}
	}
}