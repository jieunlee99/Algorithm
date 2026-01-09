import java.util.*;

class Solution {
    class Process {
        int priority;
        int index;
        
        Process(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }
    }
    
    public int solution(int[] priorities, int location) {
        
        int answer = 0; // index = location 인 프로세스가 몇번째로 실행되는지 찾아야 함
        
        // 현재 프로세스 대기 큐
        Queue<Process> queue = new LinkedList<>();
        for(int i=0; i<priorities.length; i++) {
            queue.offer(new Process(priorities[i], i));
        }
        
        while(!queue.isEmpty()) {
            Process current = queue.poll();
            
            // 현재 프로세스보다 우선순위가 높은 프로세스가 큐 안에 있는지 확인
            boolean isMax = true;
            for(Process p:queue) {
                if(p.priority > current.priority) {
                    isMax = false;
                    break;
                }
            }
            
            // 현재 프로세스의 우선순위가 가장 높다면 바로 실행
            if(isMax) {
                answer++;
                if(current.index == location) {
                    return answer;
                }
            } 
            
            // 우선순위가 더 높은 프로세스가 있다면 현재 프로세스를 맨 뒤로 보냄
            else {
                queue.offer(current);
            }
        }
        
        return answer;
    }
}