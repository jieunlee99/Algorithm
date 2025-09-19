import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		System.out.println(findAnswer(N, K));

	}

	static int findAnswer(int n, int k) {

		int[] arr = new int[n + 1];

		for (int i = 2; i <= n; i++) {
			arr[i] = i;
		}

		for (int i = 2; i <= n; i++) {
			// 이미 지웠던 숫자는 pass
			if (arr[i] == 0)
				continue;

			// 현재 숫자의 배수들을 제거함
			for (int j = i; j <= n; j += i) {
				if (arr[j] != 0) {
					arr[j] = 0;
					k--;

					// k번째 수를 찾았다면 Return
					if (k == 0) {
						return j;
					}
				}
			}

		}

		return -1;
	}
}
