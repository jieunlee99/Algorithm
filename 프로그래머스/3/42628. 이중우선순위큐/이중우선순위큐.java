import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b)-> b-a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for(int i=0; i<operations.length; i++) {
            StringTokenizer st = new StringTokenizer(operations[i]);
            
            char cmd = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());
            
            if(cmd == 'I') { // 삽입
                maxHeap.offer(Integer.valueOf(num));
                minHeap.offer(Integer.valueOf(num));
            } else if(cmd == 'D') {
                if(num == 1) { // 최댓값 삭제
                    minHeap.remove(maxHeap.poll());
                } else if(num == -1) { // 최솟값 삭제
                    maxHeap.remove(minHeap.poll());
                }
            }
        }
        
        if(minHeap.isEmpty()) {
            return new int[] {0, 0};
        } 
        
        return new int[] {maxHeap.peek(), minHeap.peek()};
    }
}