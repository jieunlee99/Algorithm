import java.util.*;

class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        int n = storage.length;
        int m = storage[0].length();
        
        char[][] map = new char[n+2][m+2];
        
        boolean[][] visited = new boolean[n+2][m+2];
        boolean[][] removed = new boolean[n+2][m+2]; 
        
        // 가장자리는 비워둠
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                map[i][j] = storage[i-1].charAt(j-1);
            }
        }
        
        // 초기 외부 접근 가능한 공간 계산
        updateVisited(visited, removed, n, m);
        
        for(int r=0; r<requests.length; r++) {
            char c = requests[r].charAt(0);
            List<int[]> targets = new ArrayList<>();
            
            if(requests[r].length() == 1) { // 지게차
                for(int i=1; i<=n; i++) {
                    for(int j=1; j<=m; j++) {
                        if(map[i][j] == c && !removed[i][j]) {
                            for(int dir=0; dir<4; dir++) {
                                if(visited[i + dx[dir]][j + dy[dir]]) { // 외부와 연결된 빈칸이 옆에 있다면
                                    targets.add(new int[]{i, j});
                                    break;
                                }
                            }
                        }
                    }
                }
            } else { // 크레인
                for(int i=1; i<=n; i++) {
                    for(int j=1; j<=m; j++) {
                        if(map[i][j] == c && !removed[i][j]) {
                            targets.add(new int[]{i, j});
                        }
                    }
                }
            }
            
            // 물건 제거 처리 및 외부 접근 통로 업데이트
            if (!targets.isEmpty()) {
                for (int[] pos : targets) {
                    removed[pos[0]][pos[1]] = true;
                }
                updateVisited(visited, removed, n, m);
            }
        }
        
        // 남은 물건 개수 세기
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(!removed[i][j]) answer++;
            }
        }
        
        return answer;
    }

    // BFS를 통해 외부(0,0)에서 도달 가능한 빈 공간들을 visited에 표시
    private void updateVisited(boolean[][] visited, boolean[][] removed, int n, int m) {
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(visited[i], false);
        }
        
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = curr[0] + dx[d];
                int ny = curr[1] + dy[d];
                if (nx >= 0 && nx < n + 2 && ny >= 0 && ny < m + 2) {
                    if (!visited[nx][ny] && (nx == 0 || nx == n + 1 || ny == 0 || ny == m + 1 || removed[nx][ny])) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}