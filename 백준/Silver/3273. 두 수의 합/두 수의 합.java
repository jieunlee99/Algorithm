import java.io.*;
import java.util.*;

public class Main {

	static int N, X;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		X = Integer.parseInt(br.readLine());

		Arrays.sort(arr);

		int start = 1, end = N;
		int cnt = 0;

		while (start < end) {
			int temp = arr[start] + arr[end];

			if (temp == X) {
				cnt++;
				start++;
				continue;
			} else if (temp < X) {
				start++;
			} else {
				end--;
			}
		}

		System.out.println(cnt);

	}
}
