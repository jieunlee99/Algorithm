import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length; // q1과 q2의 길이는 같음
        
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        
        long sumAll = 0;
        long sumQ1 = 0;
        long sumQ2 = 0;
        
        for(int i=0; i<n; i++) {
            sumAll += queue1[i];
            sumQ1 += queue1[i];
            q1.offer(queue1[i]);
        }
        
        for(int i=0; i<n; i++) {
            sumAll += queue2[i];
            sumQ2 += queue2[i];
            q2.offer(queue2[i]);
        }
        
        // 합이 홀수라면 둘로 나눌 수 없음
        if(sumAll % 2 != 0) {
            return -1;
        }
        
        long half = sumAll / 2;
        
        int cnt = 0;
        
        while(cnt <= 3*n) {
            if(sumQ1 == sumQ2) {
                return cnt;
            } else if(sumQ1 > half) {
                int num = q1.pollFirst();
                q2.offerLast(num);
            
                sumQ1 -= num;
                sumQ2 += num;
            } else {
                int num = q2.pollFirst();
                q1.offerLast(num);
            
                sumQ2 -= num;
                sumQ1 += num;
            }
            
            cnt++;
        }
        
        return -1;
    }
}