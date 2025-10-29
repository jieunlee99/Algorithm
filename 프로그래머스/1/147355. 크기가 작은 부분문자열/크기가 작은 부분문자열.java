class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        
        int tLen = t.length();
        int pLen = p.length();
        long pNum = Long.parseLong(p);
        
        for(int i=0; i<=tLen-pLen; i++) {
            long num = Long.parseLong(t.substring(i, i+pLen));
            if(num <= pNum) {
                answer++;
            }
        }
        return answer;
    }
}