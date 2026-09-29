import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] clothes = new int[n + 1];

        Arrays.fill(clothes, 1);

        for (int l : lost) {
            clothes[l]--;
        }

        for (int r : reserve) {
            clothes[r]++;
        }

        for (int i = 1; i <= n; i++) {
            if (clothes[i] == 0) {
                // 왼쪽 학생한테 빌리기
                if (i > 1 && clothes[i - 1] == 2) {
                    clothes[i - 1]--;
                    clothes[i]++;
                }
                
                // 오른쪽 학생한테 빌리기
                else if (i < n && clothes[i + 1] == 2) {
                    clothes[i + 1]--;
                    clothes[i]++;
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (clothes[i] >= 1) {
                answer++;
            }
        }

        return answer;
    }
}