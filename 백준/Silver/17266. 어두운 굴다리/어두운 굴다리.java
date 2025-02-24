import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 터널 길이
		int M = Integer.parseInt(br.readLine()); // 가로등 개수

		int[] arr = new int[M]; // 가로등 위치 배열
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 이분 탐색: 최소 조명 범위를 찾음
		int low = 1, high = N, result = N;

		while (low <= high) {
			int mid = (low + high) / 2; // 현재 가정하는 최소 조명 범위
			if (canLightAll(N, arr, mid)) { // mid 값으로 터널을 전부 비출 수 있는지 확인
				result = mid;
				high = mid - 1; // 더 작은 값이 가능한지 확인
			} else {
				low = mid + 1; // 조명 범위 증가
			}
		}

		System.out.println(result);
	}

	// 주어진 조명 범위(mid)로 터널을 전부 비출 수 있는지 확인하는 함수
	private static boolean canLightAll(int N, int[] lamps, int range) {
		int covered = 0; // 현재까지 비춰진 최대 위치

		for (int lamp : lamps) {
			if (covered < lamp - range)
				return false; // 비추지 못하는 구간이 생김
			covered = lamp + range; // 현재 가로등이 비출 수 있는 끝 위치 갱신
			if (covered >= N)
				return true; // 터널 끝까지 비출 수 있으면 가능
		}

		return covered >= N;
	}
}