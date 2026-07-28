import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        
        Set<String> set = new HashSet<>();
        
        for(String gem:gems) {
            set.add(gem);
        }

        int total = set.size();
        
        // 각 보석이 몇 번 나왔는지 체크
        Map<String, Integer> map = new HashMap<>();
        
        
        int start = 0;
        int end = gems.length-1;
        
        int left = 0;
        
        for(int right = 0; right < gems.length; right++) {
            
            map.put(gems[right], map.getOrDefault(gems[right], 0)+1);
            
            while(map.size() == total) {
                if(right - left < end - start) {
                    start = left;
                    end = right;
                }
                
                map.put(gems[left], map.get(gems[left])-1);
            
                if(map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }

                left++;
            }
        } 
        
        return new int[] {start+1, end+1};
    }
}