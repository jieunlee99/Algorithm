import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        Map<String, Integer> map =  new HashMap<>();
        
        for(int[] route:routes) {
            int time = 0;
            
            int start = route[0]-1;
            int r = points[start][0];
            int c = points[start][1];
            
            add(map, time, r, c);
            
            for(int i=1; i<route.length; i++) {
                
                int target = route[i] - 1;
                
                int nr = points[target][0];
                int nc = points[target][1];
                
                // r부터 이동
                while(r != nr) {
                    time++;
                    
                    if(r < nr) {
                        r++;
                    } else {
                        r--;
                    }
                    
                    add(map, time, r, c);
                }
                
                // c 이동
                while(c != nc) {
                    time++;
                    
                    if(c < nc) {
                        c++;
                    } else {
                        c--;
                    }
                    
                    add(map, time, r, c);
                }
            } 
        }
        
        
        int answer = 0;
        for(int count:map.values()) {
            if(count >= 2) {
                answer++;
            }
        }
        return answer;
    }
    
    private void add(Map<String, Integer> map, int time, int r, int c) {
        String key = time+","+r+","+c;
        map.put(key, map.getOrDefault(key, 0)+1);
    }
}