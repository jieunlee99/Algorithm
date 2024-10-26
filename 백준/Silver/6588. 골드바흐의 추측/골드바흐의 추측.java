
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static final int MAX = 1_000_000;
    static boolean[] isPrime = new boolean[MAX + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 에라토스테네스의 체로 소수 배열 초기화
        sieve();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            String result = solution(n);
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }

    static void sieve() {
        for (int i = 2; i <= MAX; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    static String solution(int n) {
        for (int a = 2; a <= n / 2; a++) {
            if (isPrime[a] && isPrime[n - a]) {
                return n + " = " + a + " + " + (n - a);
            }
        }
        return "Goldbach's conjecture is wrong.";
    }
}
