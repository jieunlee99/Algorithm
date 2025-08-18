import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int minLength = Integer.MAX_VALUE;

		int sum = 0;
		int low = 0, high = 0;

		while (high < N) {

			sum += arr[high++];

			while (sum >= S) {
				minLength = Math.min(minLength, high - low);
				sum -= arr[low++];
			}
		}

		System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength);
	}

}
