import java.util.*;

// StringBuilder를 스택처럼 사용함

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int n = number.length();
        
        for(int i=0; i<n; i++) {
            char current = number.charAt(i);
            
            // 1. 아직 지울 수 있는 횟수(k)가 남아있고
            // 2. 스택에 숫자가 있으며
            // 3. 스택의 마지막 숫자보다 지금 넣으려는 숫자가 더 크다면?
            // -> 스택의 마지막 숫자를 지우고 k를 줄인다.
            while(k > 0 
                  && sb.length() > 0 
                  && sb.charAt(sb.length()-1) < current) {
                sb.deleteCharAt(sb.length()-1);
                k--;
            }
            
            // 현재 숫자 추가
            sb.append(current);
        }
        
        // 루프를 다 돌았는데도 k가 남아있는 경우, 뒤에서부터 남은 k만큼 잘라내어 반환
        return sb.substring(0, sb.length()-k);
    }
}