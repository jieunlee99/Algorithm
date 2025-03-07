import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		// 입력을 빠르게 받기 위해 BufferedReader 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 첫 번째 줄 입력: N(배열 크기), K(최대 등장 가능 횟수)
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 수열을 저장할 배열
		int[] arr = new int[N];

		// 두 번째 줄 입력: 배열 원소들 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 투 포인터 (슬라이딩 윈도우) 초기화
		int left = 0, right = 0; // 왼쪽, 오른쪽 포인터
		int maxLength = 1; // 최장 연속 부분 수열 길이

		// 각 숫자의 등장 횟수를 저장할 HashMap
		Map<Integer, Integer> map = new HashMap<>();

		// 슬라이딩 윈도우 탐색 시작
		while (right < N) {
			// 현재 숫자의 등장 횟수를 확인하고, K 이하라면 윈도우 확장
			if (map.getOrDefault(arr[right], 0) + 1 <= K) {
				map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
				// 최장 길이 갱신
				maxLength = Math.max(maxLength, right - left + 1);
				right++; // 오른쪽 포인터 이동
			} else {
				// K를 초과하면 왼쪽 포인터 이동 (윈도우 축소)
				map.put(arr[left], map.get(arr[left]) - 1);
				left++; // 왼쪽 포인터 이동
			}
		}

		// 최장 연속 부분 수열의 길이 출력
		System.out.println(maxLength);
	}
}
