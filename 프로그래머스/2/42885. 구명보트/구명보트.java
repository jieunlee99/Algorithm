import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int cnt = 0;
        int left = 0, right = people.length-1;
        
        while(left<=right) {
            // 무거운 사람은 배를 혼자 탈 수도 있고 둘이 탈 수도 있음
            // 가벼운 사람과 같이 탈 수 있으면 left++
            // 무거운 사람은 무조건 타게 되므로 right--
            if(people[right--]+people[left] <= limit) {
                left++;
            }
            cnt++;
        }
        
        return cnt;
    }
}
