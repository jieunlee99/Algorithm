
class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int end = 0;

        for (int sec : section) {
            if (sec > end) {
                answer++;
                end = sec + m - 1;
            }
        }

        return answer;
    }
}
