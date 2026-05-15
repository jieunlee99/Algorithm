class Solution {
    public int solution(int n) {
        if(n<=3) return n;
        
        int[] dp = new int[n+1];
        
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        
        for(int i=4; i<=n; i++) {
            // dp[4] = dp[3] + dp[2]
            dp[i] = (dp[i-1] + dp[i-2]) % 1_000_000_007;
        }
        
        return dp[n];
    }
}