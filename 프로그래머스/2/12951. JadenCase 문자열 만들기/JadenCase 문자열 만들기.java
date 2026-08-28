import java.util.*;

class Solution {
    public String solution(String s) {
        String str = s.replaceAll(" ", " !");
        
        StringTokenizer st = new StringTokenizer(str, "!");
        StringBuilder sb = new StringBuilder();
        
        while(st.hasMoreTokens()) {
            String word = st.nextToken();
            
            System.out.println(word);
            
            char first = word.charAt(0);
            if(first >= 'a' && first <= 'z') {
                sb.append((char) (first + ('A'-'a')));
            } else {
                sb.append(first);
            }
            sb.append(word.substring(1).toLowerCase());
        }
        
        return sb.toString();
    }
}