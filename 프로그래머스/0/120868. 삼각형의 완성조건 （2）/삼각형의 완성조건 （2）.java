import java.util.*;

class Solution {
    public int solution(int[] sides) {
        int answer = 0;
        
        if(sides[0] > sides[1]) {
            int temp = sides[0];
            sides[0] = sides[1];
            sides[1] = temp;
        }
                
        int maxLen = sides[0] + sides[1] - 1;
        answer += (maxLen - sides[1]);
        answer += sides[0];
        System.out.println(sides[1] - sides[0]);
        
        return answer;
    }
}