import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 숫자 개수
		int M = Integer.parseInt(st.nextToken()); // 합

		int[] arr = new int[100_001];
		Arrays.fill(arr, 0);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;

		int sum = arr[0];
		int low = 0, high = 0;

		while (low < N && high < N) {
			if (sum > M) {
				sum -= arr[low++];
			} else if (sum < M) {
				sum += arr[++high];
			} else {
				cnt += 1;
				sum += arr[++high];
			}
		}

		System.out.println(cnt);

	}

}
