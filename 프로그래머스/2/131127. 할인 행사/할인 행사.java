import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int remain = 0;

        Map<String, Integer> need = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            need.put(want[i], number[i]);
            remain += number[i];
        }

        for (int i = 0; i < discount.length; i++) {

            // 새 상품을 윈도우에 추가
            String in = discount[i];

            if (need.containsKey(in)) {
                if (need.get(in) > 0) {
                    remain--;
                }

                need.put(in, need.get(in) - 1);
            }

            // 10일을 초과하면 가장 오래된 상품 제거
            if (i >= 10) {
                String out = discount[i - 10];

                if (need.containsKey(out)) {
                    need.put(out, need.get(out) + 1);

                    if (need.get(out) > 0) {
                        remain++;
                    }
                }
            }

            // 정확히 10일짜리 윈도우가 만들어진 시점부터 검사
            if (i >= 9 && remain == 0) {
                answer++;
            }
        }

        return answer;
    }
}