import java.util.*;

class Solution {
    int n;
    
    public int solution(String s) {
        int answer = 0;
        
        n = s.length();
        
        for(int i=0; i<n; i++) {
            if(isPossible(s, i)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public boolean isPossible(String s, int start) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=start; i<start+n; i++) {
            char cur = s.charAt(i%n);
            
            if(stack.isEmpty()) {
                if(cur != ')' || cur !=']' || cur != '}') {
                    stack.push(cur);
                } else {
                    return false;
                }
            } else {
                if((stack.peek() == '(' && cur == ')')
                    || (stack.peek() == '[' && cur == ']')
                    || (stack.peek() == '{' && cur == '}')
                  ) {
                    stack.pop();
                } else {
                    stack.push(cur);
                }
            }
        }
        
        if(stack.isEmpty()) {
            return true;
        }
        return false;
    }
}