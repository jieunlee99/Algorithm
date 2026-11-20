import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        Arrays.sort(score);
        
        int n = score.length;
        
        int start = n%m;
        
        for(int i=start; i<n; i+=m) {
            answer += (score[i] * m);
        }
        
        return answer;
    }
}