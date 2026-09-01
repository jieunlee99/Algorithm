import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for(int i=0; i<s.length(); i++) {            
            if(isRightString(s)) {
                answer++;
            }
            s = rotateString(s);
        }
        
        return answer;
    }
    
    private String rotateString(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(1));
        sb.append(s.charAt(0));
        return sb.toString();
    }
    
    private boolean isRightString(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if(c == ')') {
                if(stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if(c == '}') {
                if(stack.isEmpty() || stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if(c == ']') {
                if(stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        
        if(!stack.isEmpty()) {
            return false;
        }
        
        return true;
    }
}