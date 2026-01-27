import java.util.*;

class Solution {
    
    ArrayList<Integer>[] adjList;
    int n;
    
    public int solution(int n, int[][] wires) {
        this.n = n;
        int answer = Integer.MAX_VALUE;
        
        adjList = new ArrayList[n+1]; // 1~n까지
        for(int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<wires.length; i++) {
            // 양방향 
            adjList[wires[i][0]].add(wires[i][1]);
            adjList[wires[i][1]].add(wires[i][0]);
        }
        
        for(int i=0; i<wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            
            // 인덱스로 remove 되는 것을 방지
            adjList[v1].remove(Integer.valueOf(v2));
            adjList[v2].remove(Integer.valueOf(v1));
            
            int v1_cnt = bfs(v1);
            int v2_cnt = bfs(v2);
            answer = Math.min(answer, Math.abs(v1_cnt-v2_cnt));
            
            adjList[v1].add(v2);
            adjList[v2].add(v1);
        }
        
        return answer;
    }
    
    public int bfs(int start) {
        int cnt = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        
        queue.offer(start);
        visited[start] = true;
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            
            for(int next:adjList[current]) {
                if(!visited[next]) {
                    cnt++;
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        
        return cnt;
    }
}