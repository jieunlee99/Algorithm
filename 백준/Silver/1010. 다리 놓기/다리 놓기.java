
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp = new int[31][31];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            sb.append(combination(n, m)).append("\n");
        }

        System.out.print(sb);
    }

    static int combination(int n, int k) {

        if(n < k) {
            int temp = n;
            n = k;
            k = temp;
        }

        if(dp[n][k] != 0) {
            return dp[n][k];
        }

        if(k == 0 || n == k) {
            return dp[n][k] = 1;
        }

        return  dp[n][k] = combination(n-1, k-1) + combination(n-1, k);
    }
}
