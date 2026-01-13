import java.util.*;

class Solution {
    Set<Integer> set = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        
        // 재귀 통해서 만들어질 수 있는 숫자들을 모두 set에 추가
        recursive("", numbers); 
        
        // set에서 소수만 찾아 개수 세기
        for(int num:set) {
            if(isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public void recursive(String comb, String others) {
        // 빈 문자열이 아니라면 set에 추가
        if(!comb.equals("")) {
            set.add(Integer.parseInt(comb));
        }
        
        // 남은 글자 중 하나를 골라 새로운 조합 만들기 -> 재귀
        for(int i=0; i<others.length(); i++) {
            recursive(comb+others.charAt(i), others.substring(0, i)+others.substring(i+1));
        }
    }
    
    public boolean isPrime(int num) {
        if(num < 2) return false;
        for(int i=2; i<=(int)Math.sqrt(num); i++) {
            if(num%i == 0) {
                return false;
            }
        }
        return true;
    }
}