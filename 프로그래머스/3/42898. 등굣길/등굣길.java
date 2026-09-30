import java.util.*;

class Solution {
    
    final int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        boolean[][] blocked = new boolean[n][m];
        
        for (int[] p : puddles) {
            blocked[p[1] - 1][p[0] - 1] = true;
        }
        
        dp[0][0] = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                
                if (blocked[i][j]) {
                    dp[i][j] = 0;
                    continue;
                }
                
                if (i == 0 && j == 0) {
                    continue;
                }
                
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
                
                dp[i][j] %= MOD;
            }
        }
        
        return dp[n - 1][m - 1];
    }
}