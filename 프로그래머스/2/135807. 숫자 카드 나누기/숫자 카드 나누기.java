class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int aGcd = arrayGcd(arrayA);
        int bGcd = arrayGcd(arrayB);

        if(!divideArray(arrayA, bGcd)) {
            answer = Math.max(answer, bGcd);
        }
        
        if(!divideArray(arrayA, bGcd)) {
            answer = Math.max(answer, bGcd);
        }
        
        if(!divideArray(arrayB, aGcd)) {
            answer = Math.max(answer, aGcd);
        }
        
        if(answer == 1) return 0;
        
        return answer;
    }
    public boolean divideArray(int[] array, int num) {
        if(num == 1) {
            return false;
        }
        
        for(int a:array) {
            if(a%num == 0) return true; 
        }
        
        return false;
    }
    
    public int arrayGcd(int[] array) {
        int answer = array[0];
        for(int i=1; i<array.length; i++) {
            answer = gcd(answer, array[i]);
        }
        return answer;
    }
    
    public int gcd(int a, int b) {
        if(a % b == 0) {
            return b;
        }
        return gcd(b, a%b);
    }
}