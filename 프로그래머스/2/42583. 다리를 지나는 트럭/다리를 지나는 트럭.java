import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<bridge_length; i++) {
            queue.offer(0);
        }
        
        int index = 0; 
        
        int sum = 0;
        
        while(index < truck_weights.length) {      
            sum -= queue.poll();
            
            if(sum + truck_weights[index] <= weight) {
                queue.offer(truck_weights[index]);
                sum += truck_weights[index];
                index++;
            } else {
                queue.offer(0);
            }
            
            answer++;
        }
        
        while(!queue.isEmpty()) {
            queue.poll();
            answer++;
        }
        
        return answer;
    }
}