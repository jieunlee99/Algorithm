import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		BigInteger A = BigInteger.ONE;
		BigInteger B = BigInteger.ONE;

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			BigInteger num = new BigInteger(st.nextToken());
			A = A.multiply(num);
		}

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			BigInteger num = new BigInteger(st.nextToken());
			B = B.multiply(num);
		}

		BigInteger gcd = A.gcd(B);

		String result = gcd.toString();
		if (result.length() > 9) {
			result = result.substring(result.length() - 9);// 마지막 9자리만 남김
		}
		System.out.println(result);
	}

}
