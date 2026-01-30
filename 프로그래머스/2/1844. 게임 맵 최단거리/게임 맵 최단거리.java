import java.util.*;

class Solution {
    
    final int[] dx = {1, -1, 0, 0};
    final int[] dy = {0, 0, 1, -1};
    
    int n, m;
    boolean[][] visited;
    int minDist = Integer.MAX_VALUE;
    
    public int solution(int[][] maps) {
        
        this.n = maps.length;
        this.m = maps[0].length;
        visited = new boolean[n][m];
        
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            int cx = current[0];
            int cy = current[1];
            int dist = current[2];
            
            if(cx == n-1 && cy == m-1) {
                return dist;
            }
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(isInRange(nx, ny) && !visited[nx][ny] && maps[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny, dist+1});
                    visited[nx][ny] = true;
                }
            }            
        }
        
        return -1;
    }
    

    public boolean isInRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}