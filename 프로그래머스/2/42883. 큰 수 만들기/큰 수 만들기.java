class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < number.length(); i++) {
            char num = number.charAt(i);

            // 현재 숫자가 이전 숫자보다 크면 이전 숫자 제거
            // 제거할 수 있는 횟수(k)가 남아 있어야 함
            while (k > 0 && sb.length() > 0
                    && sb.charAt(sb.length() - 1) < num) {

                sb.deleteCharAt(sb.length() - 1);
                k--;
            }

            // 현재 숫자 추가
            sb.append(num);
        }

        // 숫자가 내림차순이면 앞에서 제거할 숫자가 없으므로
        // 남은 k개를 뒤에서 제거
        return sb.substring(0, sb.length() - k);
    }
}