import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, T;
	static int[][] A;

	static final int[] dr = { 1, -1, 0, 0 };
	static final int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		T = Integer.parseInt(st.nextToken()); // 시간

		A = new int[R][C];

		// 공기청정기가 존재하는 행
		int cleaner = -1;

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());

				if (A[i][j] == -1 && cleaner == -1) {
					cleaner = i;
				}
			}
		}

		while (T-- > 0) {
			diffuse();
			clean(cleaner);
		}

		System.out.println(calcSum());
	}

	private static int calcSum() {
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (A[i][j] <= 0)
					continue;
				sum += A[i][j];
			}
		}
		return sum;
	}

	// 공기청정기 작동 - 위쪽 공기청정기는 반시계방향, 아래쪽 공기청정기는 시계방향으로 순환
	private static void clean(int x) {
		// 첫 행부터 i행까지 반시계 방향 회전
		// x는 위쪽 공기청정기의 행 번호
		// 1. 왼쪽 벽: 위에서 아래로 당기기
		for (int i = x - 1; i > 0; i--)
			A[i][0] = A[i - 1][0];

		// 2. 천장: 오른쪽에서 왼쪽으로 당기기
		for (int i = 0; i < C - 1; i++)
			A[0][i] = A[0][i + 1];

		// 3. 오른쪽 벽: 아래에서 위로 당기기
		for (int i = 0; i < x; i++)
			A[i][C - 1] = A[i + 1][C - 1];

		// 4. 공기청정기 라인: 왼쪽에서 오른쪽으로 당기기
		for (int i = C - 1; i > 1; i--)
			A[x][i] = A[x][i - 1];

		// 5. 정화된 공기 배출 (공기청정기 바로 오른쪽)
		A[x][1] = 0;

		// i+1행부터 마지막행까지 시계 방향 회전
		int x2 = x + 1;

		// 1. 왼쪽 벽: 아래에서 위로 당기기
		for (int i = x2 + 1; i < R - 1; i++) {
			A[i][0] = A[i + 1][0];
		}

		// 2. 바닥: 오른쪽에서 왼쪽으로 당기기
		for (int i = 0; i < C - 1; i++) {
			A[R - 1][i] = A[R - 1][i + 1];
		}

		// 3. 오른쪽 벽: 위에서 아래로 당기기
		for (int i = R - 1; i > x2; i--) {
			A[i][C - 1] = A[i - 1][C - 1];
		}

		// 4. 공기청정기 라인: 왼쪽에서 오른쪽으로 당기기
		for (int i = C - 1; i > 1; i--) {
			A[x2][i] = A[x2][i - 1];
		}

		// 5. 깨끗한 공기 배출
		A[x2][1] = 0;
	}

	// 미세먼지 - 인접한 네 방향으로 확산
	// 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 X
	// 확산되는 양 = A[r][c]/5
	// (r,c)에 남은 미세먼지 양 A[r][c] -= 확산된 방향의 개수 * A[r][c]/5

	private static void diffuse() {
		int[][] nextA = new int[R][C]; // 확산 결과를 저장할 임시 배열 -> 확산되는 양은 A[r][c]의 값으로 해야되니까

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (A[i][j] == -1)
					nextA[i][j] = -1;
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (A[r][c] > 0) {
					int diffusion = A[r][c] / 5;
					int adjCnt = 0;

					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];

						if (nr >= 0 && nr < R && nc >= 0 && nc < C && A[nr][nc] != -1) {
							nextA[nr][nc] += diffusion;
							adjCnt++;
						}
					}

					nextA[r][c] += (A[r][c] - (diffusion * adjCnt));
				}
			}
		}

		A = nextA;
	}

}
