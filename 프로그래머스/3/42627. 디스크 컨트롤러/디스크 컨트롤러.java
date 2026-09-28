import java.util.*;

class Solution {
    
    class Job implements Comparable<Job> {
        int num, requestTime, workTime;
        
        public Job(int num, int requestTime, int workTime) {
            this.num = num;
            this.requestTime = requestTime;
            this.workTime = workTime;
        }
        
        @Override
        public int compareTo(Job j) {
            if(this.workTime == j.workTime) {
                if(this.requestTime == j.requestTime) {
                    return this.num - j.num;
                }
                return this.requestTime - j.requestTime;
            }
            return this.workTime - j.workTime;
        }
    }
    
    public int solution(int[][] jobs) {
        
        // 요청 시간 순 정렬
        Arrays.sort(jobs, (a, b)-> a[0] - b[0]);

        PriorityQueue<Job> pq = new PriorityQueue<>();
      
        int n = jobs.length;
        
        int time = 0;
        int index = 0;
        int total = 0;
        int completed = 0;
        
        // 모든 작업 완료할 때까지 수행
        while (completed < n) {
            
            // 요청 시간이 되면 작업 추가
            while (index < n && jobs[index][0] <= time) {
                pq.offer(new Job(index, jobs[index][0], jobs[index][1]));
                index++;
            }

            // 현재 할 수 있는 작업 모두 수행
            if (!pq.isEmpty()) {
                Job current = pq.poll();
                time += current.workTime;
                total += time - current.requestTime;
                completed++;
            } 
            
            // 할 수 있는 작업이 없으면 다음 작업으로 시간 점프
            else {
                time = jobs[index][0];
            }
        }

        // 전체 시간 / 작업 수
        return total / jobs.length;
    }
}