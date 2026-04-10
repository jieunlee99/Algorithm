import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long[] total, patty;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long X = Long.parseLong(st.nextToken());

		// total[i]: level-i일 때 햄버거의 크기(번+패티)
		// patty[i]: level-i일 때 패티의 개수
		total = new long[N + 1];
		patty = new long[N + 1];

		// level-0: "P"
		total[0] = 1;
		patty[0] = 1;

		for (int i = 1; i <= N; i++) {
			total[i] = 1 + total[i - 1] + 1 + total[i - 1] + 1;
			patty[i] = patty[i - 1] + 1 + patty[i - 1];
		}

		System.out.println(countPatties(N, X));
	}

	private static long countPatties(int n, long x) {
		if (n == 0) {
			if (x <= 0) {
				return 0;
			}
			return 1;
		}

		// total[n] = "B" + total[i - 1] + "P" + total[i - 1] + "B";
		
		// 1. 첫 번째 N-1 햄버거 구간 내에 있을 때
		if (x <= 1 + total[n - 1]) {
			return countPatties(n - 1, x - 1);
		}

		// 2. 중간 패티 위치일 때
		else if (x == 1 + total[n - 1] + 1) {
			return patty[n - 1] + 1;
		}

		// 3. 두 번째 N-1 햄버거 구간 내에 있을 때
		else if (x <= 1 + total[n - 1] + 1 + total[n - 1]) {
			return patty[n - 1] + 1 + countPatties(n - 1, x - 1 - total[n - 1] - 1);
		}

		// 4. 햄버거를 다 먹었을 때
		return patty[n];
	}
}
