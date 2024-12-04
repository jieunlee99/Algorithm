import java.util.*;
import java.io.*;

public class Main {

	static final int INF = 1000000000;
	static int N, M;
	static int[][] cost;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		cost = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(cost[i], INF);
			cost[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			cost[a][b] = Math.min(cost[a][b], c);
		}

		floyd();
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(cost[i][j] == INF) {
					cost[i][j] = 0;
				}
				sb.append(cost[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (cost[i][k] != Integer.MAX_VALUE && cost[k][j] != Integer.MAX_VALUE) {
						cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
					}
				}
			}
		}
	}
}
