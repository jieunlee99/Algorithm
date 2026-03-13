import java.util.*;

class Solution {
    Set<Integer> set = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        recursive("", numbers);
        for(int num:set) {
            if(isPrime(num)) {
                answer++;
            }
        }
        return answer;
    }
    
    public void recursive(String current, String others) {
        // 만들어진 문자열을 set에 추가함
        if(!current.equals("")) {
            set.add(Integer.parseInt(current));
        }
        
        // 현재 문자열에 캐릭터 하나를 추가하고
        // 남은 문자열에서 해당 캐릭터를 제거함
        for(int i=0; i<others.length(); i++) {
            recursive(current+others.charAt(i), others.substring(0, i)+others.substring(i+1));
        }
    }
    
    // 소수인지 확인
    public boolean isPrime(int x) {
        if(x < 2) return false;
        for(int i=2; i<=(int)Math.sqrt(x); i++) {
            if(x%i == 0) {
                return false;
            }
        }
        return true;
    }
}