import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int n = arr.length/2 + 1;
        
        int[] nums = new int[n];
        String[] ops = new String[n-1];
        
        for(int i=0; i<arr.length; i++) {
            if(i%2 == 0) {
                nums[i/2] = Integer.parseInt(arr[i]);
            } else {
                ops[i/2] = arr[i];
            }
        }
        
        int[][] max_dp = new int[n][n];
        int[][] min_dp = new int[n][n];
        
        for(int i=0; i<n; i++) {
            Arrays.fill(max_dp[i], Integer.MIN_VALUE);
            Arrays.fill(min_dp[i], Integer.MAX_VALUE);
            max_dp[i][i] = min_dp[i][i] = nums[i];
        }
        
        // O(n^3)
        for(int len=1; len<n; len++) {
            for(int i=0; i<n-len; i++) {
                int j = i+len;
                
                for(int k=i; k<j; k++) {
                    if(ops[k].equals("+")) {
                        // 최대 = 최대 + 최대, 최소 = 최소 + 최소
                        max_dp[i][j] = Math.max(max_dp[i][j], max_dp[i][k]+max_dp[k+1][j]);
                        min_dp[i][j] = Math.min(min_dp[i][j], min_dp[i][k]+min_dp[k+1][j]);
                    } else {
                        // 최대 = 최대 - 최소, 최소 = 최소 - 최대
                        max_dp[i][j] = Math.max(max_dp[i][j], max_dp[i][k]-min_dp[k+1][j]);
                        min_dp[i][j] = Math.min(min_dp[i][j], min_dp[i][k]-max_dp[k+1][j]);
                    }
                }
            }
        }
        
        return max_dp[0][n-1];
     }
}