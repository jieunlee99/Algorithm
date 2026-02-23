import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }
        
        for(int i = 0; i <= discount.length - 10; i++) {
            if(isPossibleDay(i, map, discount)) {
                answer++;
            }
        }
        return answer;
    }
    
    public boolean isPossibleDay(int start, Map<String, Integer> originMap, String[] discount) {
        Map<String, Integer> copyMap = new HashMap<>(originMap);
        
        for(int i = start; i < start + 10; i++) {
            String item = discount[i];
            if(copyMap.containsKey(item)) {
                copyMap.put(item, copyMap.get(item) - 1);
            }
        }
        
        for(int v : copyMap.values()) {
            if(v > 0) {
                return false;
            }
        }
        
        return true;
    }
}