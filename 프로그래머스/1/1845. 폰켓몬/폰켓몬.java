import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int n = nums.length; // n/2마리 선택해야 함
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int num:nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        if(map.size() >= n/2) {
            return n/2;
        } 
        return map.size();
    }
}