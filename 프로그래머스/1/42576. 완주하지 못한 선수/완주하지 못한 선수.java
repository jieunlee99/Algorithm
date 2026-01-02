import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        
        for(String p:participant) {
            map.put(p, map.getOrDefault(p, 0)+1);
        }
        
        for(String c:completion) {
            if(map.get(c) > 0) {
                map.put(c, map.getOrDefault(c, 0)-1);
            }
        }
                
        for(Map.Entry<String, Integer> entry:map.entrySet()) {
            if(entry.getValue() != 0) {
                return entry.getKey();
            }
        }
        
        
        return null;
    }
}