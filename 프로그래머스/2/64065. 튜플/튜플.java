import java.util.*;

class Solution {
    public int[] solution(String s) {
        List<Integer> tuple = new ArrayList<>();
        
        s = s.substring(2, s.length()-2);
        String[] sets = s.split("},\\{");
        
        Arrays.sort(sets, Comparator.comparingInt(String::length));
        
        for(String set:sets) {
            String[] nums = set.split(",");
            
            for (String num : nums) {
                int value = Integer.parseInt(num);
                if (!tuple.contains(value)) {  // 튜플에 없는 값이면 추가 (처음 등장한 숫자만 순서대로 추가)
                    tuple.add(value);
                }
            }
        }
                
        return tuple.stream().mapToInt(Integer::intValue).toArray();
    }
}