import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        
        long[] arr = new long[n + 1];
        for(int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + sequence[i - 1];
        }
        
        int left = 0, right = 1;
        int shortest = Integer.MAX_VALUE;
        int sl = 0, sr = 0;
        
        while(left < right && right <= n) {
            long sum = arr[right] - arr[left];
            
            if(sum == k) {
                int currentLength = right - 1 - left;
                if(currentLength < shortest) {
                    shortest = currentLength;
                    sl = left;
                    sr = right - 1;
                }
                left++; 
            } else if(sum > k) {
                left++;
                // left가 right와 같아지면 안 되므로 조정 
                if (left == right && right < n) right++;
            } else {
                right++;
            }
        }
        
        return new int[]{sl, sr};
    }
}