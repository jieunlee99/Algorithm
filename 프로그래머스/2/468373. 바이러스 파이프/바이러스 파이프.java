import java.util.*;

class Solution {
    
    List<int[]>[] adjList;
    int answer = 0;
    int n;
    
    public int solution(int n, int infection, int[][] edges, int k) {    
        this.n = n;
        
        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int[] edge:edges) {
            int a = edge[0];
            int b = edge[1];
            int type = edge[2];
            
            adjList[a].add(new int[] {b, type});
            adjList[b].add(new int[] {a, type});
        }
        
        boolean[] infected = new boolean[n+1];
        infected[infection] = true;
        
        dfs(infected, k);
        
        return answer;
    }
    
    private void dfs(boolean[] infected, int remain) {
        answer = Math.max(answer, count(infected));
        
        if(remain == 0) {
            return;
        }
        
        for(int type = 1; type <= 3; type++) {
            boolean[] next = spread(infected, type);
            dfs(next, remain-1);
        }
    }
    
    private boolean[] spread(boolean[] infected, int type) {
        boolean[] next = infected.clone();
        Queue<Integer> queue = new LinkedList<>();
        
        for(int i=1; i<=n; i++) {
            if(next[i]) {
                queue.add(i);
            }
        }
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            for(int[] edge:adjList[cur]) {
                int nextNode = edge[0];
                int edgeType = edge[1];
                
                if(edgeType != type) {
                    continue;
                }
                
                if(next[nextNode]) {
                    continue;
                }
                
                next[nextNode] = true;
                queue.offer(nextNode);
            }
        }
        
        return next;
    }
    
    private int count(boolean[] infected) {
        int cnt = 0;
        
        for(int i=1; i<=n; i++) {
            if(infected[i]) {
                cnt++;
            }
        }
        
        return cnt;
    }
}