import java.util.*;

class Solution {
    public int solution(String dirs) {        
        // 현재 위치
        int x = 0, y = 0;
        
        Set<String> path = new HashSet<>();
        
        for(int i=0; i<dirs.length(); i++) {
            char c = dirs.charAt(i);
            
            int nx = x;
            int ny = y;
            
            if(c == 'U') {
                ny++;
            } else if(c == 'D') {
                ny--;
            } else if(c == 'L') {
                nx--;
            } else if(c == 'R') {
                nx++;
            } 
            
            if(nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue;
            }
            
            path.add(edgeKey(x, y, nx, ny));
            
            x = nx;
            y = ny;
        }
        
        return path.size();
    }
    
    // 간선 방향 관계 없이 저장
    private String edgeKey(int x1, int y1, int x2, int y2) {
        if(x1 < x2 || (x1 == x2 && y1 <= y2)) {
            return x1 + "," + y1 + "->" + x2 + ","+ y2;
        } else {
            return x2 + "," + y2 + "->" + x1 + ","+ y1;
        }
    }
}