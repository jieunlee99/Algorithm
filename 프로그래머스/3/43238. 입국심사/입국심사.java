import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long left = 0;
        long right = (long)times[times.length-1]*n; 
        
        long answer = right;
        
        while(left <= right) {
            long sum = 0;
            long mid = (left+right)/2;
            
            for(int time:times) {
                sum += mid/time;
            }
            
            if(sum >= n) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        
        return answer;
    }
}