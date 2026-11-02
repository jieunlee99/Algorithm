class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        int cnt = 0;
        
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == ' ') {
                sb.append(' ');
                cnt = 0;
                continue;
            }
            
            if(cnt%2 == 0) {
                sb.append(String.valueOf(s.charAt(i)).toUpperCase());
            } else {
                sb.append(String.valueOf(s.charAt(i)).toLowerCase());
            }
            cnt++;
        }
        
        return sb.toString();
    }
}