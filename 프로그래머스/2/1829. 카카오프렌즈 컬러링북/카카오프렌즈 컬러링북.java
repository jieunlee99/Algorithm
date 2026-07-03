import java.util.*;

class Solution {
    
    int[][] picture;
    boolean[][] visited;
    
    int m, n;
    
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        this.m = m;
        this.n = n;
        this.picture = picture;
        
        this.visited = new boolean[m][n];
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(!visited[i][j] && picture[i][j] != 0) {
                    int areaSize = bfs(i, j);
                    maxSizeOfOneArea = Math.max(areaSize, maxSizeOfOneArea);
                    numberOfArea++;
                }
            }
        }

        return new int[] {numberOfArea, maxSizeOfOneArea};
    }
    
    public int bfs(int r, int c) {
        int areaSize = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            int cr = current[0];
            int cc = current[1];
            
            areaSize++;
            
            for(int i=0; i<4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                
                if(nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                
                if(!visited[nr][nc] && picture[nr][nc] == picture[cr][cc]) {
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        
        return areaSize;
    }
}