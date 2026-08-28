import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int n = people.length;
                
        int left = 0;
        for(int right=n-1; right>=left; right--) {
            if(people[left]+people[right] <= limit) {
                left++;
                answer++;
            } else {
                answer++;   
            }
        }   
        
        return answer;
    }
}