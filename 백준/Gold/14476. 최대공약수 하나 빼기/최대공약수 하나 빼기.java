import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr;
	static int[] leftToRight, rightToLeft;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		leftToRight = new int[N];
		rightToLeft = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		leftToRight[0] = arr[0];
		for (int i = 1; i < N; i++) {
			leftToRight[i] = gcd(leftToRight[i - 1], arr[i]);
		}

		rightToLeft[N - 1] = arr[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			rightToLeft[i] = gcd(rightToLeft[i + 1], arr[i]);
		}

		int maxValue = -1;
		int maxIndex = 0;

		for (int i = 0; i < N; i++) {
			int gcd = 0;

			if (i == 0) {
				gcd = rightToLeft[1];
			} else if (i == N - 1) {
				gcd = leftToRight[N - 2];
			} else {
				gcd = gcd(leftToRight[i - 1], rightToLeft[i + 1]);
			}

			if (arr[i] % gcd != 0 && maxValue < gcd) {
				maxValue = gcd;
				maxIndex = i;
			}
		}

		if (maxValue == -1) {
			System.out.println(-1);
		} else {
			System.out.println(maxValue + " " + arr[maxIndex]);
		}
	}

	static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		}

		return gcd(b, a % b);
	}
}
