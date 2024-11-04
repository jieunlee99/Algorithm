
import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[] nums;
	static boolean[] visited;
	static ArrayList<Integer> selected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N개의 자연수를 준다.
		M = Integer.parseInt(st.nextToken()); // M개를 고른다.

		nums = new int[N];
		visited = new boolean[N];
		selected = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		backtracking(0);

		System.out.println(sb.toString());
	}

	static void backtracking(int depth) {
		if (depth == M) {
			for (int num : selected) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}

		int lastNum = -1;
		for (int i = 0; i < N; i++) {
			if (!visited[i] && nums[i] != lastNum) { // 갈 수 있다면?
				visited[i] = true;
				selected.add(nums[i]);
				backtracking(depth + 1); // 간다.
				selected.remove(selected.size() - 1);
				visited[i] = false;
				lastNum = nums[i];
			}
		}

	}

}
