import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length()-2);
        String[] arr = s.split("\\},\\{");
        
        Arrays.sort(arr, (a,b)->a.length()-b.length());
        
        Set<Integer> set = new LinkedHashSet<>(); // 순서 유지
        
        for(String str:arr) {
            String[] nums = str.split(",");
            for(String num:nums) {
                set.add(Integer.parseInt(num));
            }
        }
        
        int i=0;
        int[] answer = new int[set.size()];
        for(int num:set) {
            answer[i++]=num;
        }
        
        return answer;
    }
}