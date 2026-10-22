
import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        String str = Integer.toString(n, k);

        StringTokenizer st = new StringTokenizer(str, "0");

        while (st.hasMoreTokens()) {
            long num = Long.parseLong(st.nextToken());

            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    public boolean isPrime(long num) {
        if (num < 2) return false;

        for (long i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
