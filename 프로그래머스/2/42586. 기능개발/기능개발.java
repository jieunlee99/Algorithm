import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int days = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
            q.offer(days);
        }

        while (!q.isEmpty()) {
            int day = q.poll();
            int count = 1;

            while (!q.isEmpty() && q.peek() <= day) {
                q.poll();
                count++;
            }
            
            answer.add(count); 
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}