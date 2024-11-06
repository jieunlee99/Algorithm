import java.util.*;
import java.io.*;

public class Main {

	static final int INF = 100 * 100_000 + 1;
	static int N, M;
	static int[][] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 비교 횟수
		
		dist = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
	
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dist[a][b] = Math.min(dist[a][b], 1);
		}
		
		floydWarshall();
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(dist[i][j] == INF && dist[j][i]== INF) {
					break;
				}
				
				if(j==N) cnt++;
			}
		}
		
		bw.write(String.valueOf(cnt));
		
		bw.flush();
		
		br.close();
		bw.close();
	}

	static void floydWarshall() {
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(dist[i][k] == INF || dist[k][j] == INF) {
						continue;
					}
					dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]); 
				}
			}
		}
	}
}
