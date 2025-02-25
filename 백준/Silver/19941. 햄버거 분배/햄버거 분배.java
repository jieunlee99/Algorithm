
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		char[] arr = br.readLine().toCharArray();
		boolean[] canEat = new boolean[N];

		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] == 'P') {
				int start = Math.max(i - K, 0);
				int end = Math.min(i + K, N - 1);

				for (int j = start; j <= end; j++) {
					if (arr[j] == 'H' && !canEat[j]) {
						canEat[j] = true;
						answer++;
						break;
					}
				}
			}
		}

		System.out.println(answer);
	}

}
