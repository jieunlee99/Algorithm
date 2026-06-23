import java.util.*;

class Solution {
    public int solution(int[] cards) {
        
        int n = cards.length;
        
        boolean[] visited = new boolean[n];
        
        List<Integer> list = new ArrayList<>();
        // 모든 상자 방문해야 함
        for(int i=0; i<n; i++) {
            if(visited[i]) {
                continue;
            }
            
            int cnt = 0;
            int current = i;
            
            while(!visited[current]) {
                visited[current] = true;
                cnt++;
                
                current = cards[current] - 1;
            }
            
            list.add(cnt);
        }
        
        if(list.size() < 2) {
            return 0;
        }
        
        // 내림차순 정렬 -> 가장 큰 두 수 곱함
        list.sort(Collections.reverseOrder());
        return list.get(0)*list.get(1);
    }
}