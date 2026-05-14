

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        char first = s.charAt(0);
        if(first >= 'a' && first <='z') {
            first -= 32;
        }
        sb.append(first);
        
        for(int i=1; i<s.length(); i++) {
            char before = s.charAt(i-1);
            char current = s.charAt(i);
            
            if(before == ' ') {
                if(current >= 'a' && current <='z') {
                    current -= 32;
                }
                sb.append(current);
            } else {
                if(current >= 'A' && current <='Z') {
                    current += 32;
                }
                sb.append(current);
            }
        }
        
        return sb.toString();
    }
}