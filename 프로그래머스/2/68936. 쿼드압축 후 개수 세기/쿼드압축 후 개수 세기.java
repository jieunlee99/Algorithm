class Solution {
    
    boolean[][] visited;
    
    int[] answer = {0, 0};
    
    public int[] solution(int[][] arr) {
        
        int n = arr.length;
        visited = new boolean[n][n];
        dfs(arr, 0, 0, n);
        return answer;
    }
    
    public boolean canCompress(int[][] arr, int x, int y, int size) {
        int num = arr[x][y];
        
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                if(arr[i][j] != num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void dfs(int[][] arr, int x, int y, int size) {
        
        if(canCompress(arr, x, y, size)) {
            int num = arr[x][y];
            for(int i=x; i<x+size; i++) {
                for(int j=y; j<y+size; j++) {
                    visited[i][j] = true;
                }
            }
            answer[num] += 1;
            return;
        }
        
        
        dfs(arr, x, y, size/2);
        dfs(arr, x+size/2, y, size/2);
        dfs(arr, x, y+size/2, size/2);
        dfs(arr, x+size/2, y+size/2, size/2);
    }
}