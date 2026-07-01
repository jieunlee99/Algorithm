import java.util.*;

class Solution {
    
    final int INF = 1_000_000;
   
    public int solution(int[][] info, int n, int m) {
        
        int[] dp = new int[m];
        
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for(int[] item:info) {
            // 현재 물건의 각 흔적 개수
            int aTrace = item[0];
            int bTrace = item[1];
            
            // 한 물건을 한 명만 훔치게 하기 위해 임시 배열 생성
            int[] next = new int[m];
            Arrays.fill(next, INF);
            
            for(int b=0; b<m; b++) {
                
                if(dp[b] == INF) continue;
                
                // 1. A가 훔치는 경우
                int nextA = dp[b] + aTrace;
                if(nextA < n) {
                    next[b] = Math.min(next[b], nextA);
                }
                
                // 2. B가 훔치는 경우
                int nextB = b + bTrace;
                if(nextB < m) {
                    next[nextB] = Math.min(next[nextB], dp[b]);
                }
            }
            
            // 갱신
            dp = next;
        }
        
        int answer = INF;
        
        for(int b=0; b<m; b++) {
            answer = Math.min(answer, dp[b]);
        }
        
        return answer == INF ? -1 : answer;
    }
}