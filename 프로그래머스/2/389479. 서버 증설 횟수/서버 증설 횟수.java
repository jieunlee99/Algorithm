import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        int current = 0; 
        
        for (int player : players) {
            // k시간마다 서버 정리
            if (queue.size() == k) {
                current -= queue.poll();
            }
            
            int required = player / m;
            
            // 서버가 부족하다면 부족한 만큼 증설
            if (required > current) {
                int need = required - current;
                queue.offer(need);       
                current += need;  
                answer += need;         
            } else { // 부족하지 않아도 공간 채워줘야 함
                queue.offer(0);
            }
        }
        
        return answer;
    }
}