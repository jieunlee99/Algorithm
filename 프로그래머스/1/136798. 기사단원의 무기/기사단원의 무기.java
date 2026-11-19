class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        for(int i=1; i<=number; i++) {
            int primeCnt = getPrimeCount(i);
            
            if(primeCnt > limit) {
                answer += power;
            } else {
                answer += primeCnt;
            }
        }
        
        return answer;
    }
    
    public int getPrimeCount(int number) {
        int cnt = 0;
        for(int i=1; i<=number; i++) {
            if(number%i == 0) {
                cnt++;
            }
        }
        return cnt;
    }
}