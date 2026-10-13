
import java.util.*;

class Solution {

    public int solution(String str1, String str2) {
        
        Map<String, Integer> map1 = splitStr(str1);
        Map<String, Integer> map2 = splitStr(str2);

        int intersection = 0;
        int union = 0;

        for (String key : map1.keySet()) {
            int count1 = map1.get(key);
            int count2 = map2.getOrDefault(key, 0);

            intersection += Math.min(count1, count2);
            union += Math.max(count1, count2);
        }

        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                union += map2.get(key);
            }
        }

        if (union == 0) {
            return 65536;
        }

        return (int) ((double) intersection / union * 65536);
    }

    public Map<String, Integer> splitStr(String str) {
        Map<String, Integer> map = new HashMap<>();

        str = str.toUpperCase();

        for (int i = 0; i < str.length() - 1; i++) {
            char a = str.charAt(i);
            char b = str.charAt(i + 1);

            if (a < 'A' || a > 'Z' ||
                b < 'A' || b > 'Z') {
                continue;
            }

            String key = str.substring(i, i + 2);

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return map;
    }
}
