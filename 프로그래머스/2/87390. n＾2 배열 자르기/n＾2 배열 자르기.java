import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int m = (int) (right-left+1);
        
        int[] answer = new int[m];
        
        int idx = 0;
        
        for(long l=left; l<=right; l++) {
            int i = (int) (l / n);
            int j = (int) (l % n);
            answer[idx++] = Math.max(i, j)+1;;
        }
        
        return answer;
    }
}