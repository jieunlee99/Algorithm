// 이분 탐색

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		long X = Integer.parseInt(st.nextToken()); // 게임 횟수
		long Y = Integer.parseInt(st.nextToken()); // 이긴 횟수
		long Z = Y * 100 / X; // 승률

		long answer = -1;
		long low = 0, high = Integer.MAX_VALUE;

		while (low <= high) {
			long mid = (low + high) / 2;

			// 앞으로는 무조건 이기기만 함
			long newX = X + mid;
			long newY = Y + mid;
			long newZ = newY * 100 / newX;

			if (newZ > Z) {
				answer = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		System.out.println(answer);
	}

}
