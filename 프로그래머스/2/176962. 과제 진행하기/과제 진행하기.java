import java.util.*;

class Solution {
    class Task {
        String name;
        int start;
        int playtime;
        
        public Task(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        
        for(String[] plan:plans) {
            String name = plan[0];
            String[] time = plan[1].split(":");
            int start = Integer.parseInt(time[0])*60+Integer.parseInt(time[1]);
            int playtime = Integer.parseInt(plan[2]);
            tasks.add(new Task(name, start, playtime));
        }
        
        // 시작 시간 순 정렬
        tasks.sort((t1, t2) -> t1.start - t2.start);
        
        // 대기열 (후입선출)
        Stack<Task> stack = new Stack<>();
        
        for(int i=0; i<tasks.size()-1; i++) {
            Task cur = tasks.get(i);
            Task next = tasks.get(i+1);
            
            // 현재 과제랑 다음 과제 사이의 시간을 구함
            int diff = next.start - cur.start;
            
            // 다음 과제 시작 전에 끝낼 수 있는 경우
            if(cur.playtime <= diff) {
                answer.add(cur.name);
                diff -= cur.playtime; // 남은 시간 계산
                
                // 다음 과제 시작 전에 다른 과제를 할 수 있음
                while(!stack.isEmpty() && diff > 0) {
                    Task paused = stack.peek();
                    
                    // 멈춰뒀던 과제 끝까지 가능
                    if(paused.playtime <= diff) {
                        answer.add(paused.name);
                        diff -= paused.playtime;
                        stack.pop();
                    } 
                    
                    // 멈춰뒀던 과제를 하긴 하는데 끝내진 못함
                    else {
                        paused.playtime -= diff;
                        break;
                    }
                }
            } 
            
            // 현재 과제를 다 끝내기 전에 다음 과제 시작해야 됨
            else {
                cur.playtime -= diff;
                stack.push(cur);
            }
        }
        
        // 마지막 과제의 경우 이후에 시작하는 과제가 없어서 바로 끝내기 가능
        answer.add(tasks.get(tasks.size()-1).name);
        
        // 멈춰진 과제를 순서대로 처리
        while(!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }
        
        return answer.toArray(new String[0]);
    }
}