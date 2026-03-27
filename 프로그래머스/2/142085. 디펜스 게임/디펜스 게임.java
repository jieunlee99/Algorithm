import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0; // 클리어 가능한 라운드 수
        
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<enemy.length; i++) {
            // 병사 수를 빼줌 (그리디)
            n -= enemy[i];
            pq.offer(enemy[i]);
            
            // 만약 병사 수가 부족하다면
            if(n < 0) {
                // 무적권 사용 가능하다면 그만큼 채워줌
                if (k > 0 && !pq.isEmpty()) {
                    n += pq.poll();
                    k--;
                } 
                
                // 게임 오버
                else {
                    return i;
                }
            }
        }
        
        
        return enemy.length;
    }
}