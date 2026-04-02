import java.util.*;

class Solution {
    int[][] arr;
        
    public int[] solution(int rows, int columns, int[][] queries) {
        arr = new int[rows][columns];
        
        int n = queries.length;
        int[] answer = new int[n];
        
        int num = 1;
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                arr[i][j] = num++;
            }
        }
        
        for(int i=0; i<n; i++) {
            answer[i] = rotate(queries[i]);
        }
        
        return answer;
    }

    public int rotate(int[] query) {
        int r1 = query[0] - 1, c1 = query[1] - 1, r2 = query[2] - 1, c2 = query[3] - 1;

        int temp = arr[r1][c1];
        int min = temp;

        for(int i = r1; i < r2; i++) {
            arr[i][c1] = arr[i+1][c1];
            min = Math.min(min, arr[i][c1]);
        }

        for(int i = c1; i < c2; i++) {
            arr[r2][i] = arr[r2][i+1];
            min = Math.min(min, arr[r2][i]);
        }

        for(int i = r2; i > r1; i--) {
            arr[i][c2] = arr[i-1][c2];
            min = Math.min(min, arr[i][c2]);
        }

        for(int i = c2; i > c1 + 1; i--) {
            arr[r1][i] = arr[r1][i-1];
            min = Math.min(min, arr[r1][i]);
        }

        arr[r1][c1+1] = temp;

        return min;
    }
}