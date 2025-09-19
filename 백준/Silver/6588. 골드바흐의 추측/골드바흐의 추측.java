import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static final int MAX = 1_000_000;
	static boolean[] isPrime = new boolean[MAX + 1];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		checkPrime();

		while (true) {

			int N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}

			solution(N);
		}

		System.out.print(sb);
	}

	// max까지 소수를 체크하여 배열에 저장
	static void checkPrime() {
		Arrays.fill(isPrime, true);
		for (int i = 2; i * i <= MAX; i++) {
			for (int j = i * i; j <= MAX; j += i) {
				isPrime[j] = false;
			}
		}
	}

	static void solution(int n) {
		for (int i = 2; i <= n / 2; i++) {
			if (isPrime[i] && isPrime[n - i]) {
				sb.append(n).append(" = ").append(i).append(" + ").append(n - i).append("\n");
				return;
			}
		}
		sb.append("Goldbach's conjecture is wrong.\n");
	}
}
