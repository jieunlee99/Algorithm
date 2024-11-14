
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	static class Line implements Comparable<Line> {
		int start, end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Line o) {
			return Integer.compare(this.start, o.start);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Line[] lines = new Line[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lines[i] = new Line(start, end);
		}

		Arrays.sort(lines);

		int[] dp = new int[n]; // 각 전깃줄이 LIS에서의 위치를 저장
		int[] trace = new int[n]; // LIS를 추적하기 위한 이전 인덱스를 저장
		int[] parent = new int[n]; // LIS의 각 단계에서의 마지막 요소의 인덱스를 저장
		Arrays.fill(trace, -1); // 초기화: 이전 인덱스가 없는 경우 -1로 설정

		int length = 0; // LIS의 길이를 저장할 변수
		ArrayList<Integer> lis = new ArrayList<>(); // LIS의 끝점들을 저장하는 리스트

		// 전깃줄의 끝점을 기준으로 LIS를 찾음
		for (int i = 0; i < n; i++) {
			int end = lines[i].end; // 현재 전깃줄의 끝점
			// 이분 탐색으로 LIS 리스트에서의 위치를 찾음
			int pos = Collections.binarySearch(lis, end);
			if (pos < 0)
				pos = -pos - 1; // 리스트에 없는 경우 삽입할 위치를 결정

			// pos 위치에 end 값을 갱신하거나 새로운 값을 추가
			if (pos >= lis.size()) {
				lis.add(end); // 새로운 값이 LIS의 끝에 추가되는 경우
			} else {
				lis.set(pos, end); // 기존 위치의 값을 갱신
			}

			dp[i] = pos; // 현재 전깃줄이 LIS에서의 위치를 기록

			// 현재 위치가 LIS의 일부라면, 이전 인덱스를 trace 배열에 기록
			if (pos > 0) {
				trace[i] = parent[pos - 1];
			}
			// 현재 위치를 LIS의 끝점 위치로 parent 배열에 저장
			parent[pos] = i;
		}

		// 최장 증가 부분 수열(LIS)에 포함된 전깃줄들을 selected 배열로 표시
		boolean[] selected = new boolean[n];
		int idx = parent[lis.size() - 1]; // 최장 증가 부분 수열의 마지막 인덱스부터 시작
		while (idx != -1) {
			selected[idx] = true; // 해당 인덱스가 LIS에 포함되었음을 표시
			idx = trace[idx]; // 이전 인덱스로 이동
		}

		// 제거해야 할 전깃줄의 개수와 해당 전깃줄의 시작점을 출력
		StringBuilder sb = new StringBuilder();
		sb.append(n - lis.size()).append("\n"); // 제거해야 할 전깃줄의 수

		// LIS에 포함되지 않은 전깃줄의 시작점을 출력
		for (int i = 0; i < n; i++) {
			if (!selected[i]) {
				sb.append(lines[i].start).append("\n");
			}
		}

		System.out.println(sb.toString());
	}
}
