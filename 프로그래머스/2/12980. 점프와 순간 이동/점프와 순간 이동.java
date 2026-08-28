import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        
        while(n > 0) {
            if(n%2 == 0) {
            } else {
                n-=1;
                answer++;
            }
            
            n/=2;
            
        }
        
        return answer;
    }
}