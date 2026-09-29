import java.util.*;

class Solution {
    
    int answer = Integer.MAX_VALUE;
    
    List<Integer>[] adjList;
    boolean[] visited;
    
    int n;
    int[][] wires;
    
    public int solution(int n, int[][] wires) {
        
        this.n = n;
        this.wires = wires;
        
        for(int i=0; i<n; i++) {
            int one = bfs(i);
            int two = n - one;
            answer = Math.min(answer, Math.abs(one-two));
        }
        
        return answer;
    }
    
    public int bfs(int skip) {
        
        adjList = new ArrayList[n+1];
        for(int i=0; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int i=1; i<=n; i++) {
            adjList[0].add(i);
        }
        
        visited = new boolean[n+1];
        
        for(int i=0; i<n-1; i++) {
            if(i == skip) continue;
            adjList[wires[i][0]].add(wires[i][1]);
            adjList[wires[i][1]].add(wires[i][0]);
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        
        int cnt = 1;
        queue.offer(1);
        visited[1] = true;
        
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