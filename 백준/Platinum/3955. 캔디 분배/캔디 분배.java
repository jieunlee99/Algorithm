import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int T;
	static long K, C;

	static final long MAX = 1_000_000_000; // 최대값은 10^9

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine()); // 테스트케이스의 수

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			K = Long.parseLong(st.nextToken()); // 참가자 수
			C = Long.parseLong(st.nextToken()); // 한 봉지에 들어있는 사탕의 수
			sb.append(solve(K, C)).append("\n");
		}

		System.out.print(sb);
	}

	// 사탕을 몇 봉지 준비해야 하는지 문자열로 반환
	static String solve(long k, long c) {
		// 사탕을 1개씩 판다면 참가자 수보다 한 개만 많게 준비하면 됨
		// - 범위를 만족하지 않는 경우만 불가능함
		if (c == 1) {
			return k + 1 > MAX ? "IMPOSSIBLE" : String.valueOf(k + 1);
		}

		// 참가자가 한명이라면 한 봉지만 있으면 됨
		if (k == 1) {
			return "1";
		}
		
		Result result = extendedGcd(k, c);
		// gcd가 1이어야 문제를 풀 수 있음
		if (result.gcd != 1) {
			return "IMPOSSIBLE";
		}

        // 초기 해
		long x0 = result.x;
		long y0 = result.y;
 
        // y를 자연수로 만들기 위해 K를 조정함 (방정식)
		long tMin = (long) Math.ceil((double) x0 / c);
		long y = y0 + k * tMin;

		if (y <= 0 || y > MAX) {
			return "IMPOSSIBLE";
		}
		return String.valueOf(y);
	}

	// 확장 유클리드 호제법
	static Result extendedGcd(long a, long b) {
		if (b == 0) {
			return new Result(a, 1, 0);
		}

		Result result = extendedGcd(b, a % b);
		long gcd = result.gcd;
		long x = result.y;
		long y = result.x - (a / b) * result.y;
		return new Result(gcd, x, y);
	}

	static class Result {
		long gcd, x, y;

		public Result(long gcd, long x, long y) {
			this.gcd = gcd;
			this.x = x;
			this.y = y;
		}
	}
}
