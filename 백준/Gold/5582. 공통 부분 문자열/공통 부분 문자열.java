import java.io.*;
import java.util.*;

public class Main {

	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String first = br.readLine();
		String second = br.readLine();

		int len1 = first.length();
		int len2 = second.length();
		int max = 0;

		dp = new int[len1 + 1][len2 + 1];

		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (first.charAt(i - 1) == second.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}

		System.out.println(max);
	}
}
