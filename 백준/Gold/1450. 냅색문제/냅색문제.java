import java.io.*;
import java.util.*;

public class Main {

	static int N, C;
	static int[] weights;
	static List<Integer> left, right;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 물건 수
		C = Integer.parseInt(st.nextToken()); // 최대로 담을 수 있는 무게

		weights = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}

		// 반으로 나눠서 부분합을 각각 구함
		left = new ArrayList<>();
		right = new ArrayList<>();

		int mid = N / 2;
		calcSubsetSums(left, 0, mid, 0);
		calcSubsetSums(right, mid, N, 0);

		right.sort(Integer::compareTo); // 오름차순 정렬

		int cnt = 0;
		for (int i = 0; i < left.size(); i++) {
			// 왼쪽 부분합 리스트의 각 값에 대해 오른쪽 리스트에서 남은 무게를 만족하는 최대 인덱스를 이진 탐색으로 찾다.
			int idx = upperbound(right, C - left.get(i));
			cnt += idx + 1; // 오른쪽 리스트에서 0~idx까지 현재 남은 무게를 만족하므로, idx+1만큼 더해준다.
		}

		bw.write(cnt + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	// 부분합 계산
	static void calcSubsetSums(List<Integer> list, int start, int end, int sum) {
		// 무게 제한 초과 시 바로 종
		if (sum > C)
			return;

		// 현재 부분합 추가
		if (start == end) {
			list.add(sum);
			return;
		}

		// 현재 물건을 포함하지 않는 경우와 포함하지 않는 경우를 모두 따진다.
		calcSubsetSums(list, start + 1, end, sum);
		calcSubsetSums(list, start + 1, end, sum + weights[start]);
	}

	// 무게 제한을 만족하는 조합의 개수 계산
	static int upperbound(List<Integer> list, int target) {
		int low = 0, high = list.size();

		while (low < high) {
			int mid = (low + high) / 2;
			if (list.get(mid) <= target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}

		return low - 1;
	}
}
