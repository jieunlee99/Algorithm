import java.util.*;

class Solution {

    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int work:works) {
            pq.offer(work);
        }
        
        while(n-- > 0) {
            int work = pq.poll();
            if(work == 0) {
                break;
            }
            pq.offer(work-1);
        }
        
        long answer = 0L;
        for(int v : pq) {
            answer += v*v;
        }
        
        return answer;
    }
}