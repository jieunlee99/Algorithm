import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int C = Integer.parseInt(br.readLine());

		int[] cnt = new int[1001];
		cnt[1] = 3;

		for (int i = 2; i <= 1000; i++) {
			int tmp = 0;
			for (int j = 1; j < i; j++) {
				if (gcd(i, j) == 1) {
					tmp++;
				}
			}
			cnt[i] = cnt[i - 1] + tmp * 2; // 대칭을 고려하여 tmp에 2를 곱해 더해준다.
		}

		while (C-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(cnt[N]).append("\n");
		}

		System.out.println(sb);
	}

	static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}

}
