import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(A);

		int left = 0;
		int right = 0;
		int min = Integer.MAX_VALUE;

		while (right < N) {
			int diff = A[right] - A[left];

			if (diff >= M) {
				min = Math.min(min, diff);
				left++;

				if (min == M) {
					break;
				}
			} else {
				right++;
			}
			
			//	left가 right보다 커지면 안됨으로 조정
			if(left > right) {
				right = left;
			}
		}

		System.out.println(min);
	}

}
