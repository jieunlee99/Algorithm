import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			int[] arr = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			long max = 0; // 가장 비싼 주가 저장
			long result = 0; // 이익

			for (int i = N - 1; i >= 0; i--) {
				if (max < arr[i]) {
					max = arr[i];
				} else {
					result += max - arr[i];
				}
			}

			sb.append(result).append("\n");
		}

		System.out.print(sb);
	}

}
