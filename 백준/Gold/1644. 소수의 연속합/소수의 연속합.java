import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static final int MAX = 4_000_000;
    static boolean[] isPrime = new boolean[MAX + 1];
    static ArrayList<Integer> primes = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        initIsPrime();

        // 투 포인터
        int pa = 0, pb = 0;
        int sum = primes.isEmpty() ? 0 : primes.get(0);
        int cnt = 0;

        while (pb < primes.size()) {
            if (sum < N) {
                pb++;
                if (pb < primes.size()) {
                    sum += primes.get(pb);
                }
            } else if (sum > N) {
                sum -= primes.get(pa++);
            } else { // sum == N
                cnt++;
                sum -= primes.get(pa++);
            }
        }

        System.out.println(cnt);
    }

    static void initIsPrime() {
        for (int i = 2; i <= MAX; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= MAX; i++) {
            if (isPrime[i]) {
                // j = i * i 가 MAX를 넘지 않는 경우에만 반복
                for (long j = (long) i * i; j <= MAX; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }

        for (int i = 2; i <= MAX; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }
}
