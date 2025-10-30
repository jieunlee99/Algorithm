import java.util.*;

class Solution {

    boolean[] visited;
    List<Integer>[] adjList;
    int networkCnt = 0;
    
    public int solution(int n, int[][] computers) {
        
        this.visited = new boolean[n];
        this.adjList = new ArrayList[n];
        for(int i=0; i<n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i!=j && computers[i][j] == 1) {
                    adjList[i].add(j);
                }
            }
        }
        
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                bfs(i);
                networkCnt++;
            }
        }
  
        return networkCnt;
    }
    
    void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(start);
        visited[start] =true;
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            
            for(int next:adjList[current]) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
    
    
}