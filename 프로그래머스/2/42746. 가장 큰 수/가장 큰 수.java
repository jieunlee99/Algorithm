import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        int n = numbers.length;
        
        String[] arr = new String[n];
        for(int i=0; i<n; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(arr, (n1, n2) -> (n2+n1).compareTo(n1+n2));
        if(arr[0].equals("0")) {
            return "0"; // 가장 큰 수가 0이라면 0000이어도 0
        }
        
        StringBuilder sb = new StringBuilder();
        for(String s:arr) {
            sb.append(s);
        }
        return sb.toString();
    }
}