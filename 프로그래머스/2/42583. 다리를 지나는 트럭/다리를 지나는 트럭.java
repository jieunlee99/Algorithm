import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        
        Queue<Integer> bridge = new LinkedList<>();
        
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }
        
        int sum_weight = 0;
        int idx = 0;
        
        while (idx < truck_weights.length) {
            time++;
            
            sum_weight -= bridge.poll();
            
            if (sum_weight + truck_weights[idx] <= weight) {
                bridge.offer(truck_weights[idx]);
                sum_weight += truck_weights[idx];
                idx++;
            } else {
                bridge.offer(0);
            }
        }
        
        return time + bridge_length;
    }
}