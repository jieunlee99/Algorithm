import java.io.*;
import java.util.*;

public class Main {

	static int T, N, M;
	static int[] A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		// 각 배열에서 가능한 부배열의 합을 저장
		List<Integer> subA = getSubArraySums(A, N);
		List<Integer> subB = getSubArraySums(B, M);

		Collections.sort(subB); // 이분 탐색을 위해 정렬

		long count = 0;

		// 가능한 bSum(=T-aSum)의 개수를 count
		for (int aSum : subA) {
			count += (upperbound(subB, T - aSum) - lowerbound(subB, T - aSum));
		}

		System.out.println(count);
	}

	// target 이상의 값 처음 나오는 위치
	private static int lowerbound(List<Integer> list, int target) {
		int low = 0, high = list.size();
		while (low < high) {
			int mid = (low + high) / 2;
			if (list.get(mid) >= target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	// target을 초과하는 값이 처음 나오는 위치
	private static int upperbound(List<Integer> list, int target) {
		int low = 0, high = list.size();
		while (low < high) {
			int mid = (low + high) / 2;
			if (list.get(mid) > target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private static List<Integer> getSubArraySums(int[] arr, int size) {
		List<Integer> subSums = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			int sum = 0;
			for (int j = i; j < size; j++) {
				sum += arr[j];
				subSums.add(sum);
			}
		}
		return subSums;
	}

}
