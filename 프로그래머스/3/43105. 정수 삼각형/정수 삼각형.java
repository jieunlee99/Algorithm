class Solution {
    int[][] dp;
    
    public int solution(int[][] triangle) {
        int n = triangle.length;
        dp = new int[n][n];
        
        dp[0][0] = triangle[0][0];
        
        for(int i=1; i<n; i++) {
            for(int j=0; j<=i; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                } else if(j == n-1) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j])+triangle[i][j]; 
                }
            }
        }
        
        int maxValue = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            maxValue = Math.max(dp[n-1][i], maxValue);
        }
        
        return maxValue;
    }
}