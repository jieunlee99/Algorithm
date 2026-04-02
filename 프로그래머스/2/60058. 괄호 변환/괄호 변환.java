import java.util.*;

class Solution {
    // 균형 잡힌 문자열 -> 올바른 문자열
    public String solution(String p) {
        return dfs(p);
    }
    
    public String dfs(String s) {
        // 1
        if(s.length() == 0) return "";
        
        // 2
        String u = "", v = "";
        int cnt1 = 0, cnt2 = 0; // '(', ')' 개수를 각각 저장
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            if(c == '(') cnt1++;
            else cnt2++;
            
            // 균형 잡힌 문자열일때
            if (((cnt1 > 0) && (cnt2 > 0)) && (cnt1 == cnt2)) {
                u = s.substring(0, i + 1);
                if (i < s.length() - 1) v = s.substring(i + 1, s.length());
                break;
            }
        }
        
        // 3
        if(isRightString(u)) return u + dfs(v); // 3-1

        // 4
        else {
            String tmp = "(" + dfs(v) + ")"; // 4-1, 4-2, 4-3
            // 4-4
            u = u.substring(1, u.length() - 1); 
            u = u.replace("(", ".");
            u = u.replace(")", "(");
            u = u.replace(".", ")");
            tmp = tmp + u;
            return tmp; // 4-5
        }
    }
    
    // 올바른 문자열인지 확인
    public boolean isRightString(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            if(c == '(') {
                stack.push('(');
            } 
            
            else if(c == ')') { 
                if(!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        
        return true;
    }
}