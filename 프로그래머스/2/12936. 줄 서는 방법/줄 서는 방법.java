import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        
        long total = 1;
        for(int i=1; i<=n; i++) {
            total *= i;
            list.add(i);
        }
        
        k--; // 인덱스는 0부터 시작하기 때문
        
        int index = 0;
        while(index < n) {
            total /= (n - index); // 전체/남은갯수
            answer[index++] = list.remove((int) (k/total)); 
            k %= total;
        }
        
        return answer;
    }
}