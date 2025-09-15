import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int K, N;
	static long[] primes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		PriorityQueue<Long> pq = new PriorityQueue<>();
		primes = new long[K];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			primes[i] = Long.parseLong(st.nextToken());
			pq.add(primes[i]);
		}

		long top = -1; // top을 업데이트하면서 N번째 수를 찾는다.
		while (N-- > 0) {
			top = pq.poll();
			for (long prime : primes) {
				// 범위를 넘어가면 pass
				if (top * prime >= (long) 2 << 31) {
					break;
				}

				pq.add(top * prime);

				// 중복 pass
				if (top % prime == 0) {
					break;
				}
			}
		}

		System.out.println(top);
	}

}
