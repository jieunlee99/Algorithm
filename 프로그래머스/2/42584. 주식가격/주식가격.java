import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] time = new int[prices.length];
    
        Queue<Integer> queue = new LinkedList<>();
        for(int price:prices) {
            queue.offer(price);
        }
        
        int index = 0;
        while(!queue.isEmpty()) {
            int current = queue.poll();
            int t = 0;
            
            for(int p:queue) {
                t++;
                
                if(p < current) {
                    break;
                }
            }
            
            time[index++] = t;
        }
        
    
        return time;
    }
}