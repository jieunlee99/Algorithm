import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        
        Arrays.sort(people);
        
        int answer = 0;
        int left = 0, right = people.length-1;
        
        while(left <= right) {
            // 무거운 사람은 무조건 탑승 => right--
            // 가벼운 사람은 같이 탈 수 있다면 탑승 => left++
            if(people[left]+people[right--] <= limit) {
                left++;
            }
            answer++;
        }
        
        return answer;
    }
}