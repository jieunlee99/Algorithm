import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Line[] lines;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		lines = new Line[N];
		dp = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			lines[i] = new Line(a, b);
		}

		// 'a' 기준으로 정렬
		Arrays.sort(lines);

		// dp 배열 초기화
		Arrays.fill(dp, 1);

		// 가장 긴 증가하는 부분 수열(LIS) 찾기
		int maxLIS = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (lines[j].b < lines[i].b && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
			maxLIS = Math.max(maxLIS, dp[i]);
		}

		// 제거해야 할 전깃줄 수 = 전체 개수 - 가장 긴 증가하는 부분 수열 길이
		System.out.println(N - maxLIS);
	}

}

class Line implements Comparable<Line> {
	int a, b;

	public Line(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Line o) {
		return this.a - o.a;
	}

	@Override
	public String toString() {
		return "Line [a=" + a + ", b=" + b + "]";
	}
}
