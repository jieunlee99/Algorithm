import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (t1, t2) -> t1[1]-t2[1]);
        
        int current = 0;
        
        for(int[] target:targets) {
            if(current <= target[0]) {
                current = target[1];
                answer++;
            }
        }
        
        return answer;
    }
}