import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int W, H;
    static int[][][][] dp; // 가로, 세로, 방향(0:오른쪽, 1:위쪽), 방향전환 여부(0:없음, 1:있음)
    static final int MOD = 100_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // dp[w][h][0][0]: (w, h)에 가로 방향으로 도착하며 방향 전환 없이 도달한 경우의 수.
        // dp[w][h][0][1]: (w, h)에 가로 방향으로 도착하며 방향 전환이 한 번 있었던 경우의 수.
        // dp[w][h][1][0]: (w, h)에 세로 방향으로 도착하며 방향 전환 없이 도달한 경우의 수.
        // dp[w][h][1][1]: (w, h)에 세로 방향으로 도착하며 방향 전환이 한 번 있었던 경우의 수.

        // dp 초기화
        dp = new int[W + 1][H + 1][2][2];
        for (int w = 1; w <= W; w++) {
            dp[w][1][0][0] = 1;
        }
        for (int h = 1; h <= H; h++) {
            dp[1][h][1][0] = 1;
        }

        for (int w = 2; w <= W; w++) {
            for (int h = 2; h <= H; h++) {
                dp[w][h][0][0] = (dp[w - 1][h][0][0] + dp[w - 1][h][0][1]) % MOD;
                dp[w][h][0][1] = dp[w - 1][h][1][0] % MOD;
                dp[w][h][1][0] = (dp[w][h - 1][1][0] + dp[w][h - 1][1][1]) % MOD;
                dp[w][h][1][1] = dp[w][h - 1][0][0] % MOD;
            }
        }

        int result = (dp[W][H][0][0] + dp[W][H][0][1] + dp[W][H][1][0] + dp[W][H][1][1]) % MOD;
        System.out.println(result);
    }
}
