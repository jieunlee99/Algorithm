import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[] nums;
	static ArrayList<Integer> selected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N개의 자연수를 준다.
		M = Integer.parseInt(st.nextToken()); // M개를 고른다.

		nums = new int[N];
		selected = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		backtracking(0, 0);

		System.out.println(sb.toString());
	}

	static void backtracking(int depth, int start) {
		if (depth == M) {
			for (int num : selected) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}

		int lastNum = -1;
		for (int i = start; i < N; i++) {
			if (nums[i] != lastNum) { // 갈 수 있다면?
				selected.add(nums[i]);
				backtracking(depth + 1, i + 1); // 간다.
				selected.remove(selected.size() - 1);
				lastNum = nums[i];
			}
		}

	}

}
