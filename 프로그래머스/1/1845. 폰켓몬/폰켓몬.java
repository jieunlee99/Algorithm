import java.util.*;

class Solution {
    public int solution(int[] nums) {
        // N마리 폰켓몬 중에서 N/2마리 선택 -> 종류가 많도록 선택
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int num:nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        return Math.min(map.size(), nums.length/2);
    }
}