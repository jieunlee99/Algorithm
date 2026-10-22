import java.util.*;

class Solution {
    // n: 진법
    // t: 튜브가 말해야 하는 숫자 갯수
    // m: 총 인원 수
    // p: 튜브 순서
    public String solution(int n, int t, int m, int p) {
        StringBuilder convert = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        
        for(int i = 0; convert.length() <= t * m; i++) {
            convert.append(Integer.toString(i, n));
        }
        
        for(int i = p-1; answer.length() < t; i += m) {
            answer.append(convert.charAt(i));
        }
        
        return answer.toString().toUpperCase();
    }
}