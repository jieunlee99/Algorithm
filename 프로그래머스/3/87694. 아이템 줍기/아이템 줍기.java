import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 1. 좌표 2배 확대 (최대 50 -> 100)
        // 0~100까지 표현하기 위해 102 정도의 크기 사용
        int[][] map = new int[102][102];
        
        for (int[] r : rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2, x2 = r[2] * 2, y2 = r[3] * 2;
            
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (i == x1 || i == x2 || j == y1 || j == y2) {
                        // 다른 사각형의 "내부"가 아닐 때만 "테두리(1)"로 표시
                        if (map[i][j] != 2) map[i][j] = 1;
                    } else {
                        // 사각형의 "내부"는 2로 표시
                        map[i][j] = 2;
                    }
                }
            }
        }
        
        // 2. BFS 탐색
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        boolean[][] visited = new boolean[102][102];
        Queue<int[]> queue = new LinkedList<>();
        
        // 시작점도 2배 확대
        queue.offer(new int[]{characterX * 2, characterY * 2, 0});
        visited[characterX * 2][characterY * 2] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];
            int dist = curr[2];
            
            // 목적지 도달 시 (목적지도 2배 확대 상태)
            if (cx == itemX * 2 && cy == itemY * 2) {
                // 2배 확대해서 계산했으므로 실제 거리는 2로 나눔
                return dist / 2;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                // 맵 범위 안이고, 방문하지 않았으며, 테두리(1)인 경우만 이동
                if (nx >= 0 && nx < 102 && ny >= 0 && ny < 102) {
                    if (!visited[nx][ny] && map[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }
        
        return 0;
    }
}