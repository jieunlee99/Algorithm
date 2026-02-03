import java.util.*;

class Solution {
    
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, 1, 1, 1, 0, -1 ,-1 ,-1};
    
    static Map<Node, Set<Node>> map = new HashMap<>();
    
    public int solution(int[] arrows) {
        int answer = 0;
        
        // (0,0)부터 시작
        Node cur = new Node(0, 0);
        map.put(cur, new HashSet<>());
        
        for(int i=0; i<arrows.length; i++) {
            int dir = arrows[i];
            
            // 대각선 교차점 문제를 해결하기 위해 모든 이동 거리를 2배로 늘림
            for(int j=0; j<2; j++) {
                
                int x = cur.x + dx[dir];
                int y = cur.y + dy[dir];
                
                Node next = new Node(x, y);
                
                // 다음으로 방문할 점이 아직 방문하지 않은 점이라면
                // key에 추가하여 방문 표시
                if(!map.containsKey(next)) {
                    map.put(next, new HashSet<>());
                } 
                
                // 다음으로 방문할 점이 이미 방문했지만
                // 현재 점에서는 방문하지 않았다면 방이 만들어짐
                else if(map.containsKey(next) && !map.get(cur).contains(next)) {
                    answer++;
                }
                
                // 방문 표시
                map.get(cur).add(next);
                map.get(next).add(cur);
                cur = next;
            }
        }
        
        return answer;
    }
    
    class Node {
        int x, y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        // HashSet, HashMap에 저장하기 위해 필요함
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        // 같은 Node인지 확인함
        @Override
        public boolean equals(Object o) {
            return this.x == ((Node)o).x && this.y == ((Node)o).y;
        }
    }
}