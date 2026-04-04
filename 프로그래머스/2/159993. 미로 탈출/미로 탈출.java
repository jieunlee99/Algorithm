import java.util.*;

class Solution {
    
    final int INF = Integer.MAX_VALUE;
    
    int n, m;
    char[][] arr;
    
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    
    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        
        arr = new char[n][m];
        
        int sr = -1, sc = -1;
        int er = -1, ec = -1;
        int lr = -1, lc = -1;
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                arr[i][j] = maps[i].charAt(j);
                if(arr[i][j] == 'S') {
                    sr = i;
                    sc = j;
                } else if(arr[i][j] == 'E') {
                    er = i;
                    ec = j;
                } else if(arr[i][j] == 'L') {
                    lr = i;
                    lc = j;
                }
            }
        }

        int startToLeverTime = bfs(sr, sc, lr, lc, 0);
        if(startToLeverTime == INF) {
            return -1;
        }
        
        int answer = bfs(lr, lc, er, ec, startToLeverTime);
        if(answer == INF) {
            return -1;
        }
        
        return answer;
    }
    
    public int bfs(int sr, int sc, int er, int ec, int time) {      
        int answer = INF;
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        queue.offer(new int[]{sr, sc, time});
        visited[sr][sc] = true;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            if(current[0] == er && current[1] == ec) {
                answer = Math.min(answer, current[2]);
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int nr = current[0]+dr[i];
                int nc = current[1]+dc[i];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }
                
                if(visited[nr][nc] || arr[nr][nc] == 'X') {
                    continue;
                }
                
                queue.offer(new int[]{nr, nc, current[2]+1});
                visited[nr][nc] = true;
            }
        }
        
        return answer;
    }
}