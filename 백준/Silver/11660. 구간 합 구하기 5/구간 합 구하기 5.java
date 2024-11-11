
import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = arr[i][j - 1] + num;
			}
		}

		while (M-- > 0) {			
			long answer = 0L;

			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()); 
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int x=x1; x<=x2; x++) {
				answer += (arr[x][y2]-arr[x][y1-1]);
			}	
			
			bw.write(answer+"\n");
		}
	
		bw.flush();
		
		bw.close();
		br.close();
	}

}
