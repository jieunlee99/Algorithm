class Solution {
    
    int answer = 0;
    
    int[] board;
    
    public int solution(int n) {
        board = new int[n];
        backtracking(0, n);
        return answer;
    }
    
    public void backtracking(int depth, int n) {
        if(depth == n) {
            answer++;
            return;
        }
        
        for(int i=0; i<n; i++) {
            board[depth] = i;
            if(isValid(depth)) {
                backtracking(depth+1, n);
            }
        }
    }
    
     public boolean isValid(int i) {
         
        for (int j = 0; j < i; j++) { 
            // 가로, 세로 검사
            if (board[i] == board[j]) {
                return false;
            }
            
            // 대각선 검사
            if (Math.abs(i - j) == Math.abs(board[i] - board[j])) {
                return false;
            }
        }
         
        return true;
    }
}