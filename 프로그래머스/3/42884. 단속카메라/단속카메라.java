import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        // 차량 진출 지점으로 정렬함
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] r1, int[] r2) {
                return r1[1] - r2[1]; 
            }
        });
        
        int camera = Integer.MIN_VALUE;

        for(int[] route : routes) {
            if(camera < route[0]) {
                camera = route[1];
                answer++;
            }
        }
        
        return answer;
    }
}