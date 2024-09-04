import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] sudoku = new int[9][9];

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P2580/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0,0);
    }

    public static void backtracking(int row, int col) {

        // 한 행 끝나면 다음 행 검사
        if(col == 9) {
            backtracking(row+1, 0);
            return;
        }

        // 2. 목적지라면?
        // 모든 행 검사 후 결과물 출력
        if(row == 9) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    sb.append(sudoku[i][j]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);

            // 여러 결과값 중에 하나만 출력하면 되기 때문
            System.exit(0);
        }

        if(sudoku[row][col] == 0) {
            // 3. 순회
            for(int i=1; i<=9; i++) {
                // 4. 갈 수 있다면
                if(isValid(row, col, i)) {
                    // 5. 간다.
                    sudoku[row][col] = i;
                    backtracking(row, col+1);
                }
            }

            // 6. 체크 아웃
            sudoku[row][col] = 0;
            return;
        }

        backtracking(row, col+1);
    }

    // 같은 행 검사
    // 같은 열 검사
    // 3*3 검사

    public static boolean isValid(int row, int col, int value) {

        // 0, 3, 6으로 만들어 주기
        int ty = (row/3)*3;
        int tx = (col/3)*3;

        for(int i=0; i<9; i++) {
            if(sudoku[row][i] == value) {
                return false;
            }
            if(sudoku[i][col] == value) {
                return false;
            }
        }

        for(int i=ty; i<ty+3; i++) {
            for(int j=tx; j<tx+3; j++) {
                if(sudoku[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

}
