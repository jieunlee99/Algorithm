class Solution {
    
    public int[] solution(String s) {
        int[] answer = {0, 0};
        
        while(!s.equals("1")) {
            int c = s.length(); 
            
            s = s.replace("0", "");
            
            answer[0]++;
            answer[1] += (c - s.length());
            
            c = s.length();
            s = Integer.toBinaryString(c);   
        }
        
        return answer;
    }
}