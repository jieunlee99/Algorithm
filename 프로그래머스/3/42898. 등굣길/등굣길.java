class Solution {
    
    final int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        int[][] map = new int[n+1][m+1];
        
        // 물에 잠긴 지역 표시
        for(int[] puddle:puddles) {
            map[puddle[1]][puddle[0]] = 1;
        }
        
        dp[1][1] = 1;
        
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                
                if(i==1 && j==1) {
                    continue;
                }
                
                if(map[i-1][j] == 0) {
                    dp[i][j] += dp[i-1][j];
                }
                
                if(map[i][j-1] == 0) {
                    dp[i][j] += dp[i][j-1];
                }
                
                dp[i][j] %= MOD;
            }
        }
        
        return dp[n][m];
    }
}