import java.util.*;

class Solution {
    
    int[] dx = {0, 1, -1};
    int[] dy = {1, 0, -1};
    
    public int[] solution(int n) {
        
        int m = n*(n+1)/2;
        
        int[] answer = new int[m];
        
        int[][] arr = sol(n);
        
        int cnt = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(arr[i][j] == 0) {
                    continue;
                }
                
                answer[cnt++] = arr[i][j]; 
            }
        }
         
        return answer;
    }
    
    public int[][] sol(int n) {
        int[][] arr = new int[n][n];
        
        int num = 1;
        int last = (n+1)*n/2;
        int cnt = n;
        
        int x = 0, y = -1;
        
        while(num <= last) {
            for(int i=0; i<3; i++) {
                for(int j=0; j<cnt; j++) {
                    y = y + dy[i];
                    x = x + dx[i];
                    arr[y][x] = num++;
                }
                
                cnt--;
            }
        }
        
        return arr;
    }
}
