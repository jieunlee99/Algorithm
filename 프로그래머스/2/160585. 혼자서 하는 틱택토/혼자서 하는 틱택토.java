class Solution {
    public int solution(String[] board) {
        char[][] arr = new char[3][3];

        int cntO = 0;
        int cntX = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = board[i].charAt(j);
                if (arr[i][j] == 'O') {
                    cntO++;
                } else if (arr[i][j] == 'X') {
                    cntX++;
                }
            }
        }

        // X가 O보다 많으면 불가능
        if (cntX > cntO) {
            return 0;
        }

        // O가 X보다 2개 이상 많으면 불가능
        if (cntO > cntX + 1) {
            return 0;
        }

        boolean oWin = isWin(arr, 'O');
        boolean xWin = isWin(arr, 'X');

        // 둘 다 이긴 상태는 불가능
        if (oWin && xWin) {
            return 0;
        }

        // O가 이겼다면 O가 마지막에 둔 상태여야 함
        if (oWin && cntO != cntX + 1) {
            return 0;
        }

        // X가 이겼다면 X가 마지막에 둔 상태여야 함
        if (xWin && cntO != cntX) {
            return 0;
        }

        return 1;
    }

    public boolean isWin(char[][] arr, char target) {
        for (int i = 0; i < 3; i++) {
            // 가로
            if (arr[i][0] == target && arr[i][1] == target && arr[i][2] == target) {
                return true;
            }

            // 세로
            if (arr[0][i] == target && arr[1][i] == target && arr[2][i] == target) {
                return true;
            }
        }

        // 대각선
        if (arr[0][0] == target && arr[1][1] == target && arr[2][2] == target) {
            return true;
        }

        if (arr[0][2] == target && arr[1][1] == target && arr[2][0] == target) {
            return true;
        }

        return false;
    }
}