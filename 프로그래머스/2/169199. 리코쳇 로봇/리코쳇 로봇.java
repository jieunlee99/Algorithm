import java.util.*;

class Solution {
    
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    // 빈 공간(.), 로봇의 처음 위치(R), 장애물(D), 목표지점(G)
    
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        
        char[][] arr = new char[n][m];
        boolean[][] visited = new boolean[n][m];
        
        int sx = 0, sy = 0;
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                arr[i][j] = board[i].charAt(j);
                if(arr[i][j] == 'R') {
                    sx = i;
                    sy = j;
                }
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            int cx = current[0];
            int cy = current[1];
            int dist = current[2];
            
            if(arr[cx][cy] == 'G') {
                return dist;
            }
            
            for(int i=0; i<4; i++) {
                int nx = cx;
                int ny = cy;
                
                // 미끄러지기
                while(true) {
                    int tx = nx + dx[i];
                    int ty = ny + dy[i];
                    
                    // 범위 밖 또는 장애물
                    if(tx < 0 || tx >= n || ty < 0 || ty >= m || arr[tx][ty] == 'D') {
                        break;
                    }
                    
                    nx = tx;
                    ny = ty;
                }
                
                if(!visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny, dist+1});
                    visited[nx][ny] = true;
                }
            }
        }

        return -1;
    }
}