import java.util.*;

class Solution {
    
    int[][] arr;
    boolean[][] visited;
    int n, m;
    
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        
        arr = new int[n][m];
        visited = new boolean[n][m];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                char c = maps[i].charAt(j);
                if(c == 'X') {
                    arr[i][j] = -1;
                } else {
                    arr[i][j] = c -'0';
                }
            }
        }
        
        for(int i=0; i<n; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!visited[i][j] && arr[i][j] != -1) {
                    list.add(bfs(arr, i, j));
                }
            }
        }
        
        if(list.size() == 0) return new int[] {-1};
        
        Collections.sort(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int bfs(int[][] arr, int r, int c) {
        int sum = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r, c});
        visited[r][c] = true;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            int cr = current[0];
            int cc = current[1];
            sum += arr[cr][cc];
            
            for(int i=0; i<4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                
                if(0>nr || nr>=n || 0>nc || nc>=m) {
                    continue;
                }
                
                if(visited[nr][nc]) {
                    continue;
                }
                
                if(arr[nr][nc] == -1) {
                    visited[nr][nc] = true;
                    continue;
                }            
                
                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
        
        return sum;
    }
}