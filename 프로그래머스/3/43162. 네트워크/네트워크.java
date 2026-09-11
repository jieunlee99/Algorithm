import java.util.*;

class Solution {
    
    boolean[] visited;
    List<Integer>[] adjList;
    
    int answer = 0;
    
    public int solution(int n, int[][] computers) {
        
        visited = new boolean[n];
        
        adjList = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(computers[i][j] == 1) {
                    adjList[i].add(j);
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i);
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dfs(int start) {
        visited[start] = true;
        
        for(int next : adjList[start]) {
            if(!visited[next]) {
                dfs(next);
            }
        }
    }
}