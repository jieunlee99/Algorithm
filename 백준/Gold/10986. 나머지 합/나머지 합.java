import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] arr;
	static int[] remainderCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		remainderCount = new int[M]; // 누적 합의 나머지 기록

		long cnt = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = (arr[i - 1] + Integer.parseInt(st.nextToken())) % M;
			
			if (arr[i] == 0) {
				cnt++;
			}

			cnt += remainderCount[arr[i]];
			remainderCount[arr[i]]++;
		}

		System.out.println(cnt);
	}
}
