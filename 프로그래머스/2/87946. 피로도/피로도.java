class Solution {
    
    boolean[] visited;
    
    int n;
    int[][] dungeons;
    
    int max = 0;
    
    // k -> 현재 피로도
    // dungeons -> 각 던전별 [최소 필요 피로도, 소모 피로도]가 담긴 2차원 배열
    // -> 유저가 탐험할 수 있는 최대 던전 수
    public int solution(int k, int[][] dungeons) {
        this.n = dungeons.length;
        this.dungeons = dungeons;
        
        for(int i=0; i<n; i++) {
            visited = new boolean[n];
            backtracking(i, k, 0);
        }
        
        return max;
    }
    
    public void backtracking(int dunNum, int k, int depth) {
        
        max = Math.max(max, depth);
        
        if(!visited[dunNum] && k >= dungeons[dunNum][0]) {
            
            visited[dunNum] = true;
            
            for(int i=0; i<n; i++) {
                backtracking(i, k-dungeons[dunNum][1], depth+1);
            }
            
            visited[dunNum] = false;
        }
        
        
    }
    
    
}