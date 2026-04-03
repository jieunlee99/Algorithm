import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int[][] times = new int[book_time.length][2];
        
        for (int i = 0; i < book_time.length; i++) {
            times[i][0] = timeToInt(book_time[i][0]);
            times[i][1] = timeToInt(book_time[i][1]) + 10; // 종료 시간 + 청소 시간(10분)
        }
        
        Arrays.sort(times, (a, b) -> a[0] - b[0]); // 시작 시간으로 정렬
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 종료 시간(= 사용 가능한 시간)을 넣어둠
        
        for (int[] time : times) {
            int start = time[0];
            int end = time[1];
            
            if (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll(); 
            }
            
            pq.offer(end); // 현재 손님의 종료 시간 추가
        }
        
        return pq.size();
    }
    
    public int timeToInt(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return (hour * 60) + minute;
    }
}