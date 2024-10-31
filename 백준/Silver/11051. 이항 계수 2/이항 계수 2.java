import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 10007;
    static int[][] dp = new int[1001][1001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(combination(N, K));
    }

    static int combination(int n, int k) {
        if(dp[n][k]!= 0) {
            return dp[n][k];
        }

        if(k == 0 || n == k) {
            return dp[n][k] = 1;
        }

        dp[n][k] = (combination(n-1, k-1) + combination(n-1, k))%MOD;
        return dp[n][k];
    }
}
