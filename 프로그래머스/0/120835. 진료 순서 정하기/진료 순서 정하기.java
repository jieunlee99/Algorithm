import java.util.*;

class Solution {
    public int[] solution(int[] emergency) {
        int n = emergency.length;
        
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            list.add(emergency[i]);
        }
        
        Collections.sort(list, (a, b)-> b - a);
        
        int[] answer = new int[n];
        for(int i=0; i<n; i++) {
            answer[i] = list.indexOf(emergency[i])+1;
        }
        
        return answer;
    }
}