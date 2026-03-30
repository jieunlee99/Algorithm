class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0) {
            int digit = storey % 10;
            storey /= 10;
            
            if(digit > 5) { // 위로 올라가는게 유리함
                answer += (10-digit);
                storey++;
            } else if(digit < 5) { // 내려가는게 유리함
                answer += digit;
            } else { // digit == 5
                answer += 5;
                if(storey % 10 >= 5) { 
                    storey++; // 다음 자릿수 1올림
                }
            }
        }
        
        return answer;
    }
}