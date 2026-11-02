class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        
        long pNum = Long.parseLong(p);
        int len = p.length();
        
        for(int i=0; i<t.length()-len+1; i++) {
            String sub = t.substring(i, i+len);
            
            long subNum = Long.parseLong(sub);
            
            if(subNum <= pNum) {
                answer++;
            }
        }
        
        return answer;
    }
}