import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Queue<String> cache = new LinkedList<>();
        
        for(String city:cities) {
            city = city.toLowerCase();
            
            // hit
            if(cache.contains(city)) {
                answer += 1;
                cache.remove(city);
                cache.offer(city);
            } 
            
            // miss
            else {
                answer += 5;
                if(cache.size() == cacheSize) {
                    cache.poll();
                } 
                if(cache.size() < cacheSize) {
                    cache.offer(city);
                }
            }
        }
        
        return answer;
    }
}