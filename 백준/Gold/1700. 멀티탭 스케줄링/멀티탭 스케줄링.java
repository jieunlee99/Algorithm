import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍 개수
		int K = Integer.parseInt(st.nextToken()); // 전기용품 사용 횟수

		int[] order = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}

		Set<Integer> multitab = new HashSet<>();
		int answer = 0;

		for (int i = 0; i < K; i++) {
			int current = order[i];

			if (multitab.contains(current)) continue;

			// 아직 멀티탭에 빈 자리가 있는 경우
			if (multitab.size() < N) {
				multitab.add(current);
				continue;
			}

			// 어떤 기기를 뺄지 결정
			int latestIndex = -1;
			int deviceToRemove = -1;

			for (int device : multitab) {
				int nextUse = Integer.MAX_VALUE;

				for (int j = i + 1; j < K; j++) {
					if (order[j] == device) {
						nextUse = j;
						break;
					}
				}

				// 다시 사용되지 않는 기기 발견 시 바로 제거
				if (nextUse == Integer.MAX_VALUE) {
					deviceToRemove = device;
					break;
				}

				if (nextUse > latestIndex) {
					latestIndex = nextUse;
					deviceToRemove = device;
				}
			}

			multitab.remove(deviceToRemove);
			multitab.add(current);
			answer++;
		}

		System.out.println(answer);
	}
}
