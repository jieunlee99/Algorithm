import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        
        Arrays.sort(data, new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[col-1] != b[col-1]) {
                    return a[col-1]-b[col-1];
                }
                return b[0]-a[0];
            }
        });
        
        int hashValue = hash(data, row_begin-1);
        for(int i=row_begin; i<=row_end-1; i++) {
            hashValue ^= hash(data, i);
        }
        
        return hashValue;
    }
    
    public int hash(int[][] data, int row) {
        int s = 0;
        for(int i=0; i<data[row].length; i++) {
            s += (data[row][i]%(row+1));
        }
        return s;
    }
}