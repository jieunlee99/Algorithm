import java.util.*;

class Solution {
    
    class Job implements Comparable<Job> {
        int requestTime; // 요청 시각
        int duration; // 작업 종료 시간
        
        public Job(int requestTime, int duration) {
            this.requestTime = requestTime;
            this.duration = duration;
        }

        @Override
        public int compareTo(Job j) {
            return this.duration - j.duration;
        }
    }
    
    public int solution(int[][] jobs) {
        // 'jobs'를 요청시간 오름차순으로 정렬
        Arrays.sort(jobs, (j1, j2)->j1[0]-j2[0]);
        
        PriorityQueue<Job> pq = new PriorityQueue<>();
        
        int answer = 0;
        int time = 0; // 현재 시간
        int idx = 0; // jobs 배열의 인덱스
        int cnt = 0; // 완료된 작업 수
        
        // 모든 작업이 완료될 때까지 진행
        while(cnt < jobs.length) {
            
            // 현재 시간(time) 이전에 들어온 모든 요청을 큐에 삽입
            while(idx < jobs.length && jobs[idx][0] <= time) {
                pq.offer(new Job(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            // 대기열이 비었다면 다음 작업의 요청 시간으로 점프
            if(pq.isEmpty()) {
                time = jobs[idx][0];
            } 
            
            // 대기열에서 가장 소요 시간이 짧은 작업 수행
            else {
                Job current = pq.poll();
                time += current.duration;
                answer += (time - current.requestTime);
                cnt++;
            }
        }
        
        return answer / jobs.length;
    }
}