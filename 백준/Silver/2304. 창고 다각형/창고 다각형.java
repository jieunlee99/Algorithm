import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[1001]; // 각 위치별 높이를 저장할 배열 (최대 위치: 1000)

		int start = Integer.MAX_VALUE;
		int end = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());

			arr[L] = H;

			start = Math.min(L, start);
			end = Math.max(L, end);
		}

		Stack<Integer> height = new Stack<>();

		// 왼쪽에서 오른쪽으로 높이를 비교하며 갱신함
		int temp = arr[start]; // 첫 번째 기둥의 높이를 저장함
		for (int i = start + 1; i <= end; i++) {
			if (arr[i] < temp) {
				height.push(i); // 현재 높이가 temp보다 작으면 스택에 위치 저장
			} else {
				while (!height.isEmpty()) {
					int x = height.pop();
					arr[x] = temp;
				}
				temp = arr[i];
			}
		}
		height.clear();

		// 오른쪽에서 왼쪽으로 높이를 비교하며 갱신함
		temp = arr[end];
		for (int i = end - 1; i >= start; i--) {
			if (arr[i] < temp) {
				height.push(i); // 현재 높이가 temp보다 작으면 스택에 위치 저장
			} else {
				while (!height.isEmpty()) {
					int x = height.pop();
					arr[x] = temp;
				}
				temp = arr[i];
			}
		}

		int result = 0;
		for (int i = start; i <= end; i++) {
			result += arr[i];
		}

		sb.append(result);
		System.out.println(sb);
	}

}