
class Solution {

    int[] nums;
    int answer = 0;

    public int solution(int[] nums) {
        this.nums = nums;

        dfs(0, 0, 0);

        return answer;
    }

    public void dfs(int depth, int sum, int start) {
        if (depth == 3) {
            if (isPrime(sum)) {
                answer++;
            }
            return;
        }

        for (int i = start; i < nums.length; i++) {
            dfs(depth + 1, sum + nums[i], i + 1);
        }
    }

    public boolean isPrime(int num) {
        if (num < 2) return false;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
