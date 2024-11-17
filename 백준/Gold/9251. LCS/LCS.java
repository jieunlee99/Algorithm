import java.util.*;
import java.io.*;

public class Main {

	static int[][] dp;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] input1 = br.readLine().toCharArray();
		char[] input2 = br.readLine().toCharArray();

		int len1 = input1.length;
		int len2 = input2.length;

		dp = new int[len1 + 1][len2 + 1];
		
		for(int i=1; i<=len1; i++) {
			for(int j=1; j<=len2; j++) {
				if(input1[i-1] == input2[j-1]) {
					dp[i][j] = dp[i-1][j-1]+1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[len1][len2]);
	}
}
