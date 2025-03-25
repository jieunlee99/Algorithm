import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // N이 작은 경우 바로 출력
        if (N == 1 || N == 3 || N == 4) {
            System.out.println("SK");
            return;
        }
        if (N == 2) {
            System.out.println("CY");
            return;
        }

        boolean[] dp = new boolean[N + 1];

        dp[1] = true;  // SK 승
        dp[2] = false; // CY 승
        dp[3] = true;  // SK 승
        dp[4] = true;  // SK 승

        for (int i = 5; i <= N; i++) {
            // 한 번이라도 CY가 지는 경우가 있으면 SK가 승리할 수 있음
            dp[i] = !(dp[i - 1] && dp[i - 3] && dp[i - 4]);
        }

        System.out.println(dp[N] ? "SK" : "CY");
    }
}
