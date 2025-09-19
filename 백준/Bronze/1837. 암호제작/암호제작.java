import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

	static boolean[] isPrime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		String Pstr = st.nextToken();
		int K = Integer.parseInt(st.nextToken());

		BigInteger P = new BigInteger(Pstr);

		// 1. K까지 소수 구하기
		isPrime = new boolean[K];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i * i < K; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j < K; j += i) {
					isPrime[j] = false;
				}
			}
		}

		// 2. 소수로 나눗셈 확인 (K까지의 숫자 중에 나누어 떨어지는 수가 있으면 안 됨)
		for (int i = 2; i < K; i++) {
			if (isPrime[i]) {
				if (mod(P, i)) {
					System.out.println("BAD " + i);
					return;
				}
			}
		}

		// 3. 나누어떨어지는 게 없으면 GOOD
		System.out.println("GOOD");
	}

	private static boolean mod(BigInteger P, int i) {
		return P.mod(BigInteger.valueOf(i)).equals(BigInteger.ZERO);
	}
}