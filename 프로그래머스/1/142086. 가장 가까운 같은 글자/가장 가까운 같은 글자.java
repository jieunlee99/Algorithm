import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] result = new int[s.length()];
        
        Map<Character, Integer> map = new HashMap<>();
        
        for(int cur=0; cur<s.length(); cur++) {
            char c = s.charAt(cur);
            
            int before = map.getOrDefault(c, -1);
            
            if(before == -1) {
                result[cur] = -1;
            } else {
                result[cur] = cur - before;
            }
            
            map.put(c, cur);
        }
        
        return result;
    }
}