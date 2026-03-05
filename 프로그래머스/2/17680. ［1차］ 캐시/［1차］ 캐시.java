import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Deque<String> cache = new ArrayDeque<>();
        
        for(int i=0; i<cities.length; i++) {
            
            String city = cities[i].toLowerCase();
            
            if(cache.contains(city)) {
                cache.remove(city);
                answer+=1;
            } else {
                answer += 5;
            }
            
            cache.addLast(city);
            
            if (cache.size() > cacheSize) {
                cache.pollFirst();
            }
        }
        
        return answer;
    }

}