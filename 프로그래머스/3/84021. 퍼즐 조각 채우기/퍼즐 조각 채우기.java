import java.util.*;

class Solution {
    
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        
        int n = game_board.length;
        
        // 빈 공간과 퍼즐 조각을 각각 저장
        List<List<int[]>> spaces = extractShapes(game_board, 0);
        List<List<int[]>> pieces = extractShapes(table, 1);
        
        boolean[] used = new boolean[pieces.size()];
        
        for(List<int[]> space:spaces) {
            for(int i=0; i<pieces.size(); i++) {
                if(used[i]) {
                    continue;
                }
                
                List<int[]> piece = pieces.get(i);
                
                if(space.size() != piece.size()) {
                    continue;
                }
                
                boolean matched = false;
                for(int r=0; r<4; r++) {
                    if(isSame(space, piece)) {
                        matched = true;
                        break;
                    }
                    piece = rotate(piece);
                }
                
                if(matched) {
                    answer += space.size();
                    used[i] = true;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    // 보드에서 연결된 모양(퍼즐 또는 빈 공간)을 추출하여 리스트로 반환
    private List<List<int[]>> extractShapes(int[][] board, int target) {
        int n = board.length;
        
        boolean[][] visited = new boolean[n][n];
        List<List<int[]>> shapes = new ArrayList<>();
        
        for(int i=0; i<n ; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == target && !visited[i][j]) {
                    shapes.add(bfs(board, visited, i, j, target));
                }
            }
        }
        
        return shapes;
    }
    
    // 모양 추출 함수 (한 모양을 이루고 있는 좌표들을 List에 추가함) 
    private List<int[]> bfs(int[][] board, boolean[][] visited, int x, int y, int target) {
        int n = board.length;
        
        List<int[]> shape = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            shape.add(cur);
        
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if(0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if(!visited[nx][ny] && board[nx][ny] == target) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        
        return normalize(shape);
    }
    
    // shape의 좌표들을 (0, 0) 기준으로 이동시키고 정렬함
    private List<int[]> normalize(List<int[]> shape) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        for(int[] pos:shape) {
            minX = Math.min(minX, pos[0]);
            minY = Math.min(minY, pos[1]);
        }
        
        List<int[]> normalized = new ArrayList<>();
        for(int[] pos:shape) {
            normalized.add(new int[] {pos[0]-minX, pos[1]-minY});
        }
        
        // shape 비교 위해 좌표 정렬 
        normalized.sort((a, b) -> {
            if(a[0] != b[0]) {
                return  a[0] - b[0];
            }
            return a[1] - b[1];
        });
        
        return normalized;
    }
    
    // 모양을 90도 회전시킴 (x, y) -> (y, -x)
    private List<int[]> rotate(List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();
        for(int[] pos:shape) {
            rotated.add(new int[] {pos[1], -pos[0]});
        }
        return normalize(rotated);
    }
    
    // 두 모양이 일치하는지 확인
    private boolean isSame(List<int[]> shape1, List<int[]> shape2) {
        for(int i=0; i<shape1.size(); i++) {
            if(shape1.get(i)[0] != shape2.get(i)[0]
              || shape1.get(i)[1] != shape2.get(i)[1]) {
                return false;
            }
        }
        return true;
    }
}