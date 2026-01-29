import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        if(N == number) {
            return 1;
        }
        
        List<Set<Integer>> dp = new ArrayList<>();
        
        // 최솟값이 8보다 크면 -1을 return, 1~8의 경우만 생각하면 됨.
        for(int i=0; i<=8; i++) {
            dp.add(new HashSet<>());
        }
        
        dp.get(1).add(N); // N을 한 번 사용하여 N 만들기 가능
        
        for(int i=2; i<=8; i++) {
            // N을 i번 반복한 숫자 add
            StringBuilder sb = new StringBuilder();
            for(int j=1; j<=i; j++) {
                sb.append(N);
            }
            dp.get(i).add(Integer.parseInt(sb.toString()));
            
            // 사칙연산으로 만들어질 수 있는 수 add
            for(int j=1; j<i; j++) {
                for (int num1 : dp.get(j)) {
                    for (int num2 : dp.get(i-j)) {
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 - num2);
                        dp.get(i).add(num1 * num2);
                        if (num2 != 0) {
                            dp.get(i).add(num1 / num2);
                        }
                    }
                }
            }
            
            // i번 안에 만들 수 있다면 i를 return
            if (dp.get(i).contains(number)) {
                return i;
            }
        }
        
        // 최솟값이 8보다 크면 -1 return
        return -1;
    }
}