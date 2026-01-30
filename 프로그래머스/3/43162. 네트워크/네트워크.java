import java.util.*;

class Solution {
    
    ArrayList<Integer>[] adjList;
    boolean[] visited;
    int network;
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        
        adjList = new ArrayList[n];
        for(int i=0; i<n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i != j && computers[i][j] == 1) {
                    adjList[i].add(j);
                }
            }
        }
        
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                dfs(i);
                network++;
            }
        }
        
        return network;
    }
    
    public void dfs(int current) {
        visited[current] = true;
        for(int next:adjList[current]) {
            if(!visited[next]) {
                dfs(next);
            }
        }
    }
}