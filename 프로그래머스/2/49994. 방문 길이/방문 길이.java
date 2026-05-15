import java.util.*;

class Solution {
    public int solution(String dirs) {
        Set<String> visitedPaths = new HashSet<>();
        
        int curX = 0;
        int curY = 0;
        
        for (int i = 0; i < dirs.length(); i++) {
            char dir = dirs.charAt(i);
            
            int nextX = curX;
            int nextY = curY;
            
            if (dir == 'U') nextY++;
            else if (dir == 'D') nextY--;
            else if (dir == 'R') nextX++;
            else if (dir == 'L') nextX--;
            
            if (nextX < -5 || nextX > 5 || nextY < -5 || nextY > 5) {
                continue; 
            }
            
            String path1 = curX + "," + curY + "->" + nextX + "," + nextY;
            String path2 = nextX + "," + nextY + "->" + curX + "," + curY;
            
            visitedPaths.add(path1);
            visitedPaths.add(path2);
            
            curX = nextX;
            curY = nextY;
        }
        
        // 양방향으로 저장됨으로 반으로 나눠줘야 함
        return visitedPaths.size() / 2;
    }
}