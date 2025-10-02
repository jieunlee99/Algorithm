

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] num;
	static boolean[] visited;
	static List<Integer> selected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		num = new int[N];
		visited = new boolean[N];
		selected = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);

		backtracking(0, 0);

		System.out.println(sb);

	}

	static void backtracking(int depth, int start) {

		if (depth == M) {
			for (int num : selected) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}

		int lastVisitNum = -1;

		for (int i = start; i < N; i++) {
			if (num[i] != lastVisitNum) {

				visited[i] = true; // 체크인
				selected.add(num[i]);

				backtracking(depth + 1, i + 1); // Go

				selected.remove(selected.size() - 1); // 체크아웃
				visited[i] = false;

				lastVisitNum = num[i]; // 중복되는 값이 들어올 수 있기 때문에 저장함
			}
		}
	}

}
