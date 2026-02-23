import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 1};
        
        Set<String> set = new HashSet<>();
        
        String last = words[0];
        set.add(last);
        
        for(int i=1; i<words.length; i++) {
            
            if(set.contains(words[i]) || 
               last.charAt(last.length()-1) != words[i].charAt(0)) {
                answer[0] = (i+1)%n;
                if(answer[0] == 0) answer[0] = n;
                return answer;
            }
            
            if(i%n == n-1) {
                answer[1]++;
            }
            set.add(words[i]);
            last = words[i];

            if(i==words.length-1) {
                return new int[] {0,0};
            } 
        }

        return answer;
    }
}