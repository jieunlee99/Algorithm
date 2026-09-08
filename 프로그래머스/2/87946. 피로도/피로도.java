class Solution {
    int answer = 0;
    
    int n;
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        visited = new boolean[n];
        
        dfs(dungeons, k, 0);
        
        return answer;
    }
    
    public void dfs(int[][] dungeons, int k, int depth) {
        for(int i=0; i<dungeons.length; i++) {
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(dungeons, k-dungeons[i][1], depth+1);
                visited[i] = false;
            }
        }
        
        answer = Math.max(depth, answer);
    }
}