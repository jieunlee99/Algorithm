import java.util.*;

class Solution {
    
    final int INF = Integer.MAX_VALUE;
    
    public int solution(int x, int y, int n) {
        
        int[] dp = new int[y+1];
        
        Arrays.fill(dp, INF);  
        dp[x] = 0;
        
        for(int i=x; i<=y; i++) {
            
            if(dp[i] == INF) continue;
            
            if(i+n <= y) {
                dp[i+n] = Math.min(dp[i+n], dp[i]+1);
            }
            
            if(i*2 <= y) {
                dp[i*2] = Math.min(dp[i*2], dp[i]+1);
            }
            
            if(i*3 <= y) {
                dp[i*3] = Math.min(dp[i*3], dp[i]+1);
            }
            
        }
        
        return dp[y] == INF ? -1 : dp[y];
    }
}