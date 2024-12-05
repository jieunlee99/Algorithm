import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int p1 = 0;
		int p2 = N - 1;

		int min = Integer.MAX_VALUE;
		int answer1 = -1, answer2 = -1;

		while (p1 < p2) {
			int sum = arr[p1] + arr[p2];
			int abs = Math.abs(sum);

			if (min > abs) {
				min = abs;
				answer1 = arr[p1];
				answer2 = arr[p2];
			}

			if (sum < 0) {
				p1++;
			} else {
				p2--;
			}
		}

		System.out.println(answer1 + " " + answer2);
	}
}
