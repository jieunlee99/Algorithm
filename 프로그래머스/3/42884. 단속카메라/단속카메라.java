import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        // 고속도로에서 나간 시점으로 정렬
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] r1, int[] r2) {
                return r1[1] - r2[1];
            }
        });
        
        int lastCamera = Integer.MIN_VALUE;
        
        for(int[] route:routes) {
            if(lastCamera < route[0]) {
                lastCamera = route[1];
                answer++;
            }
        } 
        
        return answer;
    }
}