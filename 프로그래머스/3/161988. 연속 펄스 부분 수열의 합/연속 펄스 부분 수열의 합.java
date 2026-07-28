import java.util.*;

class Solution {
    
    public long solution(int[] sequence) {        
        int n = sequence.length;
        
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        
        long cur1 = 0;
        long cur2 = 0;
        
        for(int i=0; i<n; i++) {
            long p1 = (i%2 == 0) ? sequence[i] : -sequence[i];
            long p2 = -p1;
            
            cur1 = Math.max(p1, cur1 + p1);
            cur2 = Math.max(p2, cur2 + p2);
            
            max1 = Math.max(max1, cur1);
            max2 = Math.max(max2, cur2);
        }
        
        return Math.max(max1, max2);
    }
}