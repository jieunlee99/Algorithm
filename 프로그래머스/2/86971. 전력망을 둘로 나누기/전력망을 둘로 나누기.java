import java.util.*;

class Solution {
    
    ArrayList<Integer>[] adjList;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int[] wire:wires) {
            adjList[wire[0]].add(wire[1]);
            adjList[wire[1]].add(wire[0]);
        } 
        
        for(int[] wire:wires) {
            int u = wire[0];
            int v = wire[1];
            
            adjList[u].remove(Integer.valueOf(v));
            adjList[v].remove(Integer.valueOf(u));
            
            int cnt1 = bfs(n, u);
            int cnt2 = bfs(n, v);
            answer = Math.min(answer, Math.abs(cnt1-cnt2));
            
            adjList[u].add(v);
            adjList[v].add(u);
        }
        
        return answer;
    }
    
    // 한 전력망에 몇 개의 송전탑이 연결되어 있는지 개수 반환
    public int bfs(int n, int start) {
        int cnt = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        
        queue.offer(start);
        visited[start] = true;
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            
            for(int next:adjList[current]) {
                if(!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}