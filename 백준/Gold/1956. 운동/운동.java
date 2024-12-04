import java.io.*;
import java.util.*;

public class Main {
	public static final int INF = 4000001;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		dist = new int[V + 1][V + 1];
		for (int i = 1; i <= V; i++) {
			Arrays.fill(dist[i], INF);
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dist[a][b] = c;
		}

		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
				}
			}
		}

		int answer = INF;
		for (int i = 1; i <= V; i++) {
			answer = Math.min(answer, dist[i][i]);
		}

		System.out.println(answer == INF ? -1 : answer);

	}
}