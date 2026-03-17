class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        char[][] map = new char[m][n];
        for(int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        } 

        while(true) {
            // 2*2 찾아서 지우기
            int cnt = erase(m, n, map);
            if(cnt == 0) break;
            answer += cnt;

            // 빈 공간 채우기 (블록 내리기)
            drop(m, n, map);
        }
        
        return answer;
    }

    private int erase(int m, int n, char[][] map) {
        int cnt = 0;
        
        // 지워질 칸들 체크
        boolean[][] check = new boolean[m][n];

        for(int i = 0; i < m - 1; i++) {
            for(int j = 0; j < n - 1; j++) {
                if(map[i][j] == '.') continue;
                
                // 네 칸이 동일한지 확인
                char cur = map[i][j];
                if(map[i+1][j] == cur && map[i][j+1] == cur && map[i+1][j+1] == cur) {
                    check[i][j] = check[i+1][j] = check[i][j+1] = check[i+1][j+1] = true;
                }
            }
        }

        // 지우기 & 갯수 세기
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(check[i][j]) {
                    cnt++;
                    map[i][j] = '.';
                }
            }
        }
        return cnt;
    }

    // 빈 칸 채우면서 내리기
    // 각 열 별로 가장 아래 행부터 위로 올라가면서 빈 칸 체크
    private void drop(int m, int n, char[][] map) {
        for(int j = 0; j < n; j++) {
            for(int i = m - 1; i >= 0; i--) {
                if(map[i][j] == '.') {
                    for(int k = i - 1; k >= 0; k--) {
                        if(map[k][j] != '.') {
                            map[i][j] = map[k][j];
                            map[k][j] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }
}