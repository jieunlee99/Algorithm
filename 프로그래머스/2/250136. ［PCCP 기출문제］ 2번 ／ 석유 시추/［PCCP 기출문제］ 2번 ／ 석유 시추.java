import java.util.*;

class Solution {
    
    boolean[][] visited;
    int[][] land;
    int n, m;
    
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] land) {
        this.land = land;
        this.n = land.length;
        this.m = land[0].length;

        visited = new boolean[n][m];
        
        int[] oilSize = new int[n*m+2];

        int landNum = 2;
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!visited[i][j] && land[i][j] == 1) {
                    oilSize[landNum] = bfs(landNum, i, j);
                    landNum++;
                }
            }
        }
                
        int answer = 0;
        
        for(int col = 0; col < m; col++) {
            Set<Integer> set = new HashSet<>();
            int sum = 0;
            
            for(int row = 0; row < n; row++) {
                int num = land[row][col];
                
                if(num >= 2 && !set.contains(num)) {
                    set.add(num);
                    sum += oilSize[num];
                }
            }
            
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
    
    public int bfs(int landNum, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        
        int cnt = 0;
        
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        land[x][y] = landNum;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            int cx = current[0];
            int cy = current[1];
            
            cnt++;
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];                
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                
                if(!visited[nx][ny] && land[nx][ny] == 1) {
                    queue.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    land[nx][ny] = landNum;
                }
            }
        }
        
        return cnt;
    }
}