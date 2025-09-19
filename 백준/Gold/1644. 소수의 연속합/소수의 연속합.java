import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static boolean[] isPrime;
	static List<Integer> primes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		init(N);

		int cnt = 0, sum = 0;
		int left = 0, right = 0;

		while (true) {
			if (sum >= N) {
				if (sum == N)
					cnt++;
				sum -= primes.get(left++);
			} else {
				if (right == primes.size())
					break;
				sum += primes.get(right++);
			}
		}

		System.out.println(cnt);
	}

	static void init(int limit) {
		isPrime = new boolean[limit + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;

		for (int i = 2; i * i <= limit; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j <= limit; j += i) {
					isPrime[j] = false;
				}
			}
		}

		for (int i = 2; i <= limit; i++) {
			if (isPrime[i])
				primes.add(i);
		}
	}
}
