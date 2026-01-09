import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 오름차순 정렬
        for(int s:scoville) {
            pq.offer(s);
        }
        
        int cnt = 0;
        int currentScoville = 0;
        
        while(!pq.isEmpty()) {
            
            int first = pq.poll();
            if(first >= K) {
                return cnt;
            }
            
            if(pq.isEmpty()) {
                break;
            }
            int second = pq.poll();
            
            currentScoville = first + second*2;
            pq.offer(currentScoville);
            
            cnt++;
        }
        
        return -1;
    }
}