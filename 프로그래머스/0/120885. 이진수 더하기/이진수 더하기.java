class Solution {
    public String solution(String bin1, String bin2) {
        int num1 = Integer.valueOf(bin1, 2);
        int num2 = Integer.valueOf(bin2, 2);
        
        return Integer.toBinaryString(num1+num2);
    }
}