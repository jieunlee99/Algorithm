import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        
        int nextIndex = 1;
        for (int i = 0; i < 26; i++) {
            map.put(String.valueOf((char)('A' + i)), nextIndex++);
        }
        
        List<Integer> ansList = new ArrayList<>();
        int i = 0;
        
        while (i < msg.length()) {
            String w = "";
            int j = i;
            
            while (j < msg.length() && map.containsKey(msg.substring(i, j + 1))) {
                w = msg.substring(i, j + 1);
                j++;
            }
            
            ansList.add(map.get(w));
            
            if (j < msg.length()) {
                String wc = msg.substring(i, j + 1);
                map.put(wc, nextIndex++);
            }
            
            i += w.length();
        }
        
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }
}