import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String op:operations) {
            StringTokenizer st = new StringTokenizer(op);
            char cmd = st.nextToken().charAt(0); // I or D
            int num = Integer.parseInt(st.nextToken());
            
            if(cmd == 'I') {
                minHeap.offer(num);
                maxHeap.offer(num);
            } else if(cmd == 'D') {
                
                if(minHeap.isEmpty()) {
                   continue; 
                }
                
                if(num == -1) {
                    int min = minHeap.poll();
                    maxHeap.remove(min);
                } else if(num == 1) {
                    int max = maxHeap.poll();
                    minHeap.remove(max);
                } 
            }
        }
        
        if(minHeap.size() >= 1) {
            answer[0] = maxHeap.peek();
            answer[1] = minHeap.peek();
        }
        
        return answer;
    }
}