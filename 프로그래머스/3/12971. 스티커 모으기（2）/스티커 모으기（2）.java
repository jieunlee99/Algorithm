class Solution {
    public int solution(int[] sticker) {
        int n = sticker.length;

        if (n == 1) {
            return sticker[0];
        }

        int[][] dp = new int[n][2];

        /*
         * dp[i][0]: 첫 번째 스티커를 뜯지 않은 경우,
         *           i번째까지 얻을 수 있는 최대 합
         *
         * dp[i][1]: 첫 번째 스티커를 뜯은 경우,
         *           i번째까지 얻을 수 있는 최대 합
         */

        // 첫 번째 스티커를 뜯지 않는 경우
        dp[0][0] = 0;
        dp[1][0] = sticker[1];

        // 첫 번째 스티커를 뜯는 경우
        dp[0][1] = sticker[0];
        dp[1][1] = sticker[0];

        for (int i = 2; i < n; i++) {
            // 첫 번째 스티커를 뜯지 않았으므로 마지막 스티커까지 선택 가능
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][0] + sticker[i]);

            // 첫 번째 스티커를 뜯었으면 마지막 스티커는 선택할 수 없음
            if (i < n - 1) {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][1] + sticker[i]);
            }
        }

        return Math.max(dp[n - 1][0], dp[n - 2][1]);
    }
}