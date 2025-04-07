import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;
	public static int[][] arr;
	public static BigInteger[][] dp;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		dp = new BigInteger[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dfs(0, 0, N));
	}

	private static BigInteger dfs(int x, int y, int n) {
		if (x == n - 1 && y == n - 1)
			return BigInteger.ONE;
		
		if (dp[x][y] != null)
			return dp[x][y]; // 이미 계산된 값 반환
		
		dp[x][y] = BigInteger.ZERO; // 초기값 설정
		
		for (int i = 0; i < 2; i++) {
			int nextX = x + dx[i] * arr[x][y];
			int nextY = y + dy[i] * arr[x][y];
			
			if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
				dp[x][y] = dp[x][y].add(dfs(nextX, nextY, n));
			}
		}
		return dp[x][y];
	}

	public static int dx[] = { 1, 0 };
	public static int dy[] = { 0, 1 };
}