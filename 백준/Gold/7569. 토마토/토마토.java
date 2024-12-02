import java.io.*;
import java.util.*;

public class Main {

	static int M, N, H;
	static int[][][] box;
	static final int[] dh = { 1, -1, 0, 0, 0, 0 };
	static final int[] dn = { 0, 0, 1, 0, -1, 0 };
	static final int[] dm = { 0, 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		box = new int[H][N][M];

		Queue<int[]> queue = new LinkedList<>();

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					box[h][n][m] = Integer.parseInt(st.nextToken());
					if (box[h][n][m] == 1) {
						queue.add(new int[] { h, n, m });
					}
				}
			}
		}

		int cnt = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int[] current = queue.poll();

				for (int d = 0; d < 6; d++) {
					int nh = current[0] + dh[d];
					int nn = current[1] + dn[d];
					int nm = current[2] + dm[d];

					if (isInRange(nh, nn, nm) && box[nh][nn][nm] == 0) {
						box[nh][nn][nm] = 1; // 익은 상태로 업데이트
						queue.add(new int[] { nh, nn, nm });
					}
				}
			}
			cnt++;
		}

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (box[h][n][m] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}

		System.out.println(--cnt);

	}

	static boolean isInRange(int h, int n, int m) {
		return 0 <= h && h < H && 0 <= n && n < N && 0 <= m && m < M;
	}
}
