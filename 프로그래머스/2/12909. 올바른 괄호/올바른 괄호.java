import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            if(c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if(stack.isEmpty()) {
                    return false;
                }
                
                if(stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        
         if(stack.isEmpty()) {
            return true;
        }
        return false;
    }
}