import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];

		for (int i = N; i >= 1; i--) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int cnt = 0;
		int i = 1;
		while (K > 0) {
			while (K >= arr[i]) {
				K -= arr[i];
				cnt++;
			}
			i++;
		}
		
		System.out.print(cnt);
	}

}
