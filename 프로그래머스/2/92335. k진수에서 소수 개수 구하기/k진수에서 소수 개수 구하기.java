class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String str = makeBaseK(n, k);
        String[] arr = str.split("0");
        
        for(String a : arr) {
            if(a.equals("")) continue;
            
            long num = Long.parseLong(a); 
            if(isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // 매개변수와 반복문 변수 타입을 long으로 변경
    public boolean isPrime(long num) {
        if(num == 1) return false;
        if(num == 2) return true;
        
        for(long i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
        
    public String makeBaseK(int n, int k) {
        StringBuilder sb = new StringBuilder();
        
        while(n > 0) {
            sb.append(n % k);
            n /= k;
        }
        
        return sb.reverse().toString();
    }
}