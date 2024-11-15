import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] memory, cost;
	static int[][] dp; // dp[i][j]는 i번째 앱까지 고려할 때 j 비용으로 확보할 수 있는 최대 메모리를 의미
	
	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N =Integer.parseInt(st.nextToken());
		M =Integer.parseInt(st.nextToken());

		memory = new int[N+1];
		cost = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		int costSum = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			costSum += cost[i];
		}
		
		dp = new int[N+1][costSum+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<=costSum; j++) {
				if(cost[i] > j) {
					dp[i][j] = dp[i-1][j];
				} else {
					dp[i][j]= Math.max(dp[i-1][j], memory[i]+dp[i-1][j-cost[i]]);
				}
			}
		}
		
		int minCost = Integer.MAX_VALUE;
		for(int j=0; j<=costSum; j++) {
			if(dp[N][j]>=M)  {
				minCost = j;
				break;
			}
		}
		
		System.out.println(minCost);
	}

}
