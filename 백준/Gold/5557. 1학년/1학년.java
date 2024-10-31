import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;
    static int result;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N - 1];
        dp = new long[N - 1][21]; // 계산할 때 값의 범위 : 0<=X<=20

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        result = Integer.parseInt(st.nextToken());

        // 초기값 설정
        dp[0][nums[0]] = 1;

        // DP 수행
        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] > 0) {
                    int plus = j + nums[i];
                    int minus = j - nums[i];

                    if (plus <= 20) dp[i][plus] += dp[i - 1][j];
                    if (minus >= 0) dp[i][minus] += dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N - 2][result]);
    }
}
