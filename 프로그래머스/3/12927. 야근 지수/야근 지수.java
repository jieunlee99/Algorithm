import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < works.length; i++) {
            queue.offer(works[i]);
        }
        
        while(n > 0) {
            int cur = queue.poll();
            if(cur == 0) break;
            cur--;
            n--;
            queue.offer(cur);
        }
        
        while(!queue.isEmpty()) {
            int t = queue.poll();
            answer += t * t;
        }
        
        return answer;
    }
}