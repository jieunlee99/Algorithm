import java.io.*;
import java.util.*;

public class Main {

	static int N, C;
	static int[] houses;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		houses = new int[N];

		// 1 2 8 4 9
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}

		// 1 2 4 8 9
		Arrays.sort(houses);

		int result = 0;

		int low = 1;
		int high = houses[N - 1] - houses[0]; // 최대 간격

		while (low <= high) {
			int mid = (low + high) / 2;

			if (canPlaceRouters(mid)) {
				result = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		System.out.println(result);

	}

	static boolean canPlaceRouters(int distance) {

		// 첫 번째 집에 일단 설치된 상태
		int count = 1;
		int lastPosition = houses[0];

		for (int i = 1; i < N; i++) {
			if (houses[i] - lastPosition >= distance) {
				count++;
				lastPosition = houses[i];
			}
		}

		return count >= C;
	}
}
