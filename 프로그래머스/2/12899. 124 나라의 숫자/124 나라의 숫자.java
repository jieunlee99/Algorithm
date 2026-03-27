class Solution {
    public String solution(int n) {
        String answer = "";
        String[] numbers = {"4","1","2"};
        
        while(n > 0){
            int num = n % 3;
            
            if(num == 0){
                n--;
            }
            
            answer = numbers[num] + answer;
            
            n /= 3;
        }
        
        return answer;
    }
}