import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);

        int n = citations.length;
        
        int left = 0;
        int right = n - 1;
        
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int h = n - mid;

            if (citations[mid] >= h) {
                answer = h;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}