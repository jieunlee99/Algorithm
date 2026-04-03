class Solution {
    public int solution(int[][] board) {
        int maxLen = 0;
        
        int n = board.length;
        int m = board[0].length;
        
        // 행이나 열의 길이가 1인 경우 -> 예외 처리
        if (n < 2 || m < 2) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1) return 1;
                }
            }
            return 0;
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (board[i][j] == 1) {
                    int up = board[i-1][j];         // 위쪽
                    int left = board[i][j-1];       // 왼쪽
                    int leftUp = board[i-1][j-1];   // 왼쪽 대각선 위
                    
                    // DP 업데이트
                    board[i][j] = Math.min(up, Math.min(left, leftUp)) + 1; 
                    
                    maxLen = Math.max(maxLen, board[i][j]);
                }
            }
        }
        
        return maxLen * maxLen;
    }
}