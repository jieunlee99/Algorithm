import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 1_000_000_000;
    static int N, M, K;
    static int[][] dp ;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // a 개수
        M = Integer.parseInt(st.nextToken()); // z 개수
        K = Integer.parseInt(st.nextToken()); // 사전에서 K번째 문자열을 출력하라 (없을 시 -1 출력)

        dp = new int[N+1][M+1];
        calculateCombinations();

        if(dp[N][M] < K) {
            System.out.println(-1);
        } else {
            System.out.println(findKthString(N, M, K));
        }

    }

    static void calculateCombinations() {
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=M; j++) {
                if(j == 0 || i == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(dp[i-1][j]+dp[i][j-1], MAX);
                }
            }
        }
    }

    static String findKthString(int n, int m, int k) {
        if (n == 0) {
            return "z".repeat(m);
        }
        if (m == 0) {
            return "a".repeat(n);
        }

        if (dp[n - 1][m] >= k) {
            return "a" + findKthString(n - 1, m, k);
        } else {
            return "z" + findKthString(n, m - 1, k - dp[n - 1][m]);
        }
    }
}
