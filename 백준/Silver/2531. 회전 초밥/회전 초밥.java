import java.io.*;
import java.util.*;

public class Main {
	static int[] arr, menus;
	static int N, d, k, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 접시 수
		d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		arr = new int[N];
		menus = new int[d + 1];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int answer = 0;
		int count = 0; // 현재 고유한 초밥 가짓수

		// 초기 k개의 초밥 선택
		for (int i = 0; i < k; i++) {
			if (menus[arr[i]] == 0)
				count++;
			menus[arr[i]]++;
		}

		// 쿠폰 초밥 추가
		if (menus[c] == 0)
			count++;
		menus[c]++;

		answer = count;

		// 슬라이딩 윈도우
		for (int i = 1; i < N; i++) {
			int removeIndex = arr[i - 1]; // 이전 왼쪽 초밥 제거
			menus[removeIndex]--;
			if (menus[removeIndex] == 0)
				count--;

			int addIndex = arr[(i + k - 1) % N]; // 새로운 초밥 추가 (원형 배열)
			if (menus[addIndex] == 0)
				count++;
			menus[addIndex]++;

			answer = Math.max(answer, count);
		}

		System.out.println(answer);
	}
}