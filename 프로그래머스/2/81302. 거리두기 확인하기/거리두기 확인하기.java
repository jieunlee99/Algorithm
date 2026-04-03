import java.util.*;

class Solution {
    
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0; i<5; i++) {
            answer[i] = check(places[i]) ? 1 : 0;
        }
        return answer;
    }
    
    public boolean check(String[] place) {
        char[][] arr = parsing(place);
        
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(arr[i][j] == 'P') {
                    if(!bfs(arr, i, j)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    public boolean bfs(char[][] arr, int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        
        queue.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            int dist = cur[2];
            
            if(dist >= 2) {
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                
                if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) {
                    continue;
                }
                
                if(visited[nr][nc]) {
                    continue;
                }
                
                if(arr[nr][nc] == 'X') {
                    continue;
                }
                
                if(arr[nr][nc] == 'P') {
                    return false;
                }
                
                queue.offer(new int[] {nr, nc, dist+1});
                visited[nr][nc] = true;
            }
        }
        return true;
    }
    
    public char[][] parsing(String[] place) {
        char[][] arr = new char[5][5];
        for(int i=0; i<5; i++) {
            arr[i] = place[i].toCharArray();
        }
        return arr;
    }
}