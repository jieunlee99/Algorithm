import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 100 * 100_000 + 1;
	static int N, M;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 키 비교 횟수

		dist = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			// a가 b보다 작다
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dist[a][b] = Math.min(dist[a][b], 1);
		}

		fw();

		// 자기 키가 몇번째인지 알 수 있는 사람의 수
		int answer = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 자신과 비교하지 않은 사람이 있다면 no
				if (dist[i][j] == INF && dist[j][i] == INF) {
					break;
				}
				
				if (j == N)
					answer++;
			}
		}
		
		System.out.println(answer);
	}

	static void fw() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][k] == INF || dist[k][j] == INF) {
						continue;
					}
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
}
