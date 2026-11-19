
class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;

        String find = String.valueOf(k);

        while (i <= j) {
            String num = String.valueOf(i);

            for (int a = 0; a < num.length(); a++) {
                if (num.charAt(a) == find.charAt(0)) {
                    answer++;
                }
            }

            i++;
        }

        return answer;
    }
}
