import java.util.*;

class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(c >= 'a' && c <= 'z') {
                sb.append((char)('a' + (c - 'a' + n) % 26));
            } else if(c >= 'A' && c <= 'Z') {
                sb.append((char)('A' + (c - 'A' + n) % 26));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}