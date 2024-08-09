import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] map;
	static int[][] dp; // dp[i][j]: (i, j)를 최우측 최하단으로 하는 가장 큰 정사각형의 한 변의 길이

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
//		System.setIn(new FileInputStream("src/DAY10/P1915/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			String input = br.readLine();
			for(int j=1; j<=M; j++) {
				map[i][j] = input.charAt(j-1)-'0';
			}
		}
		
//		for(int i=1; i<=N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
		
		int max = Integer.MIN_VALUE;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == 1) {
					dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]), dp[i-1][j-1])+1;
					if(max < dp[i][j]) {
						max = dp[i][j];
					}
				}
			}
		}
		
//		for(int i=1; i<=N; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		
		System.out.println(max*max);
	}

}
