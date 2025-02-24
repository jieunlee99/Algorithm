import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		long[] visit = new long[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			visit[i] = visit[i - 1] + Long.parseLong(st.nextToken());
		}

		long max = 0;
		int count = 0;

		for (int i = X; i <= N; i++) {
			long temp = visit[i] - visit[i - X];

			if (temp > max) {
				max = temp;
				count = 1; // 새로운 최대값이면 count 초기화
			} else if (temp == max) {
				count++; // 같은 최대값이면 count 증가
			}
		}

		StringBuilder sb = new StringBuilder();
		if (max == 0) {
			sb.append("SAD");
		} else {
			sb.append(max).append("\n").append(count);
		}

		System.out.println(sb);
	}
}
