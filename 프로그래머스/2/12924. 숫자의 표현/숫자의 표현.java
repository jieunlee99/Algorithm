import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int start = 1; // 윈도우의 시작
        int end = 1;   // 윈도우의 끝
        int sum = 1;   // 윈도우의 합
        
        // n 자신만으로도 하나의 경우가 되므로, end의 범위는 n까지입니다.
        // start는 end를 따라잡을 수 있습니다 (예: n=15일 때 15 하나만으로도 경우의 수)
        while (start <= n) {
            
            if (sum == n) {
                // 1. 합이 n과 같은 경우
                answer++;
                // 윈도우를 축소하여 다음 경우의 수를 찾음
                sum -= start;
                start++;
            } else if (sum < n) {
                // 2. 합이 n보다 작은 경우
                // 윈도우를 확장
                end++;
                // n 자신 (15)의 경우를 처리하기 위해 end가 n을 넘어가면 탐색 중지
                if (end > n) {
                    break;
                }
                sum += end;
            } else {
                // 3. 합이 n보다 큰 경우
                // 윈도우를 축소
                sum -= start;
                start++;
            }
        }
        
        return answer;
    }
}