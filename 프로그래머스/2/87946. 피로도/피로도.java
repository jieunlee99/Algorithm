class Solution {
    
    int[][] dungeons;
    boolean[] visited;
    
    int n, k;
    
    int answer = Integer.MIN_VALUE;
    
    public int solution(int k, int[][] dungeons) {
        this.n = dungeons.length;
        this.dungeons = dungeons;
        this.visited = new boolean[n];
        
        dfs(k, 0);
        
        return answer;
    }
    
    public void dfs(int current, int depth) {
        if(depth > answer) {
            answer = Math.max(depth, answer);
        }
        
        if(depth == n) {
            return;
        }
        
        for(int i=0; i<n; i++) {
            if(!visited[i] && dungeons[i][0] <= current) {
                visited[i] = true;
                dfs(current-dungeons[i][1], depth+1);
                visited[i] = false;
            }
        }
    }
}