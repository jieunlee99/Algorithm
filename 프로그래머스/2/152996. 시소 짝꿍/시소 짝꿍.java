import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        int n = weights.length;
        
        Arrays.sort(weights);
                
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                if(isPossible(weights[i], weights[j])) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    // a <= b
    private boolean isPossible(int a, int b) {
        if(a == b) return true;
        if(a*4 == b*3) return true;
        if(a*4 == b*2) return true;
        if(a*3 == b*2) return true;
        return false;
    }
}