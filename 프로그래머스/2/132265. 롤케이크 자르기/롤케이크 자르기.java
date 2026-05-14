import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int n = topping.length;
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        HashSet<Integer> leftSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            leftSet.add(topping[i]);
            left[i] = leftSet.size();
        }
        
        HashSet<Integer> rightSet = new HashSet<>();
        for (int i = n-1; i >= 0; i--) {
            rightSet.add(topping[i]);
            right[i] = rightSet.size();
        }
        
        int cnt = 0;
        for (int i = 0; i < n-1; i++) {
            if (left[i] == right[i+1]) {
                cnt++;
            }
        }
        
        return cnt;
    }
}