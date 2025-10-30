class Solution {
    public int[] solution(String s) {
        
        int repeat = 0;
        int remove = 0;
        
        while(!s.equals("1")) {
            int before = s.length();
            int after = s.replaceAll("0", "").length();
            
            remove += (before-after);
            
            s = Integer.toBinaryString(after);
            
            repeat++;
        }
        
        
        return new int[]{repeat, remove};
    }
}