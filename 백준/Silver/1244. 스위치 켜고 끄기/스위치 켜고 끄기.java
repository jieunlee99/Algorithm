import java.io.*;
import java.util.*;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 스위치 개수

		boolean[] switches = new boolean[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			switches[i] = st.nextToken().equals("1"); // 1이면 true, 0이면 false
		}

		int M = Integer.parseInt(br.readLine()); // 학생 수

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken()) - 1; // 1-based index → 0-based index 변환

			if (sex == 1) { // 남학생
				for (int j = k; j < N; j += (k + 1)) {
					switches[j] = !switches[j];
				}
			} else { // 여학생
				int left = k, right = k;

				// 대칭 확장
				while (left - 1 >= 0 && right + 1 < N && switches[left - 1] == switches[right + 1]) {
					left--;
					right++;
				}

				// 해당 범위 스위치 반전
				for (int j = left; j <= right; j++) {
					switches[j] = !switches[j];
				}
			}
		}

		// 결과 출력 (한 줄에 20개씩 출력)
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(switches[i] ? "1 " : "0 ");
			if ((i + 1) % 20 == 0)
				sb.append("\n");
		}

		System.out.print(sb);
	}
}
