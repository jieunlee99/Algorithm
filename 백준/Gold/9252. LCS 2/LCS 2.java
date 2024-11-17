import java.io.*;
import java.util.*;

public class Main {

	static char[] input1, input2;
	static int len1, len2;
	static int[][] dp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		input1 = br.readLine().toCharArray();
		input2 = br.readLine().toCharArray();

		len1 = input1.length;
		len2 = input2.length;

		dp = new int[len1 + 1][len2 + 1];

		lcs();

		makeString(len1, len2);

		System.out.println(dp[len1][len2]);
		if (dp[len1][len2] != 0) {
			System.out.println(sb.toString());
		}
	}

	static void lcs() {
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (input1[i - 1] == input2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
	}

	static void makeString(int i, int j) {
		Stack<Character> stack = new Stack<>();

		while (i > 0 && j > 0) {
			if (dp[i][j] == dp[i - 1][j]) {
				i--;
			} else if (dp[i][j] == dp[i][j - 1]) {
				j--;
			} else {
				stack.push(input1[i - 1]);
				i--;
				j--;
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
	}
}
