import java.util.*;

class Solution {
    
    int n, m;
    
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        boolean[][] visited = new boolean[n][m];
        
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            int r = current[0];
            int c = current[1];
            int depth = current[2];
            
            if(r == n - 1 && c == m - 1) {
                return depth;
            }
            
            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }
                
                if(!visited[nr][nc] && maps[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, depth + 1});
                }
            }
        }
        
        return -1;
    }
}