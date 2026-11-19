import java.util.*;

class Solution {
    public int solution(String my_string) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        String[] arr = my_string.split(" ");
        
        for(int i=0; i<arr.length; i++) {
            if(arr[i].equals("+")) {
                continue;
            } else if(arr[i].equals("-")) {
                arr[i+1] = "-"+ arr[i+1];
            } else {
                answer += Integer.parseInt(arr[i]);
            }
        }
        
        return answer;
    }
}