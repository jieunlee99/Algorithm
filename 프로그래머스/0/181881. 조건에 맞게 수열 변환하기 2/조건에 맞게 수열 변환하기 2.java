import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        
        while(true) {
            if(func(arr)) {
                break;
            }
            answer++;
        }
        
        return answer;
    }
    
    public boolean func(int[] arr) {
        
        int[] temp = Arrays.copyOf(arr, arr.length);
        
        for(int i=0; i<arr.length; i++) {
            if(arr[i] > 50 && arr[i] % 2 == 0) {
                arr[i] = arr[i] / 2;
            } else if(arr[i] < 50 && arr[i] % 2 == 1) {
                arr[i] = arr[i] * 2 + 1;
            }
        }
        
        for(int i=0; i<arr.length; i++) {
            if(temp[i] != arr[i]) {
                return false;
            }
        }
        return true;
    }
}