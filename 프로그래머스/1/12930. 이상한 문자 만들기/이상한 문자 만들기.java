class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        int cnt = 0;
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            if(c == ' ') {
                sb.append(c);
                cnt = 0;
                continue;
            }
            
            if(cnt%2 == 0) {
                sb.append(String.valueOf(c).toUpperCase());
            } else {
                sb.append(String.valueOf(c).toLowerCase());
            }
            cnt++;
        }
        
        return sb.toString();
    }
}