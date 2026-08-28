import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        Set<Integer> set = new HashSet<>();

        // 시작 위치
        for (int i = 0; i < n; i++) {
            int sum = 0;

            // 연속 부분 수열의 길이
            for (int j = 0; j < n; j++) {
                sum += elements[(i + j) % n];
                set.add(sum);
            }
        }

        return set.size();
    }
}