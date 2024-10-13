
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P2014/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] primes = new long[K];
        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            primes[i] = Long.parseLong(st.nextToken());
            pq.add(primes[i]);
        }

        long top = -1;
        while (--N >= 0) {
            top = pq.poll();
            for (long prime : primes) {
                // 범위를 벗어난다면 pass
                if (top * prime >= (long) 2 << 31) break;

                pq.add(top * prime);

                // top이 어떤 소수 prime으로 나누어 떨어진다는 것은 이미 그 소수와 곱해서 나온 값이라는 의미
                if (top % prime == 0) break;
            }
        }

        System.out.println(top);
    }
}
