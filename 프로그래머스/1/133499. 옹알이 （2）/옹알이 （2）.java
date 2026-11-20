
class Solution {
    public int solution(String[] babbling) {
        int answer = 0;

        for (String b : babbling) {
            if (b.contains("ayaaya") ||
                b.contains("yeye") ||
                b.contains("woowoo") ||
                b.contains("mama")) {
                continue;
            }

            b = b.replaceAll("aya", " ");
            b = b.replaceAll("ye", " ");
            b = b.replaceAll("woo", " ");
            b = b.replaceAll("ma", " ");

            if (b.trim().equals("")) {
                answer++;
            }
        }

        return answer;
    }
}
