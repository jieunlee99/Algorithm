import java.util.*;
import java.io.*;

public class Main {

	static final int INF = 1000000000;
	static int N, M;
	static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					graph[i][j] = 0;
				} else {
					graph[i][j] = INF;
				}
			}
		}

		StringTokenizer st;
		int a, b, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph[a][b] = Math.min(graph[a][b], c);
		}

		floyd();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				
				if(graph[i][j] == INF) {
					graph[i][j] = 0;
				}
				
				bw.write(graph[i][j] + " ");
			}
			bw.write("\n");
		}

		bw.flush();

		bw.close();
		br.close();
	}

	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (graph[i][k] != Long.MAX_VALUE && graph[k][j] != Long.MAX_VALUE) {
						graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
					}
				}
			}
		}
	}

}
