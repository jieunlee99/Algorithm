import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 트럭 수
		int w = Integer.parseInt(st.nextToken()); // 다리 길이
		int L = Integer.parseInt(st.nextToken()); // 다리 최대하중

		int[] trucks = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			trucks[i] = Integer.parseInt(st.nextToken());
		}

		Queue<Integer> bridge = new ArrayDeque<>();
		int time = 0; // 시간 경과
		int weightSum = 0; // 현재 다리 위 총 하중
		int idx = 0; // 다음에 올라갈 트럭 인덱스

		// 다리 위를 초기에는 모두 0으로 채워줌 (빈 공간)
		for (int i = 0; i < w; i++) {
			bridge.offer(0);
		}

		while (idx < n) {
			time++;

			// 1초가 지남 -> 다리 위에서 한 칸씩 이동 (가장 앞칸은 나감)
			weightSum -= bridge.poll();

			// 다음 트럭이 올라갈 수 있는지 확인
			if (weightSum + trucks[idx] <= L) {
				bridge.offer(trucks[idx]);
				weightSum += trucks[idx];
				idx++; // 다음 트럭 준비
			} else {
				// 트럭 못 올라감 → 빈 공간 (0)으로 채움
				bridge.offer(0);
			}
		}

		// 마지막 트럭이 다리를 다 건너기 위해 추가로 w초 필요
		System.out.println(time + w);
	}
}
