import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;
        
        // 정렬
        Arrays.sort(citations);
        
        // 이진 탐색
        int answer = 0;
        int low = 0;
        int high = n;
        
        while(low <= high) {
            int mid = (low+high)/2;
            
            // 모든 논문은 0번이상 인용되기 때문에 검사할 필요 x
            if(mid == 0) {
                low = mid+1;
                continue;
            }
            
            // h번 이상 인용된 논문이 h개 이상인지 확인
            if(citations[n-mid] >= mid) {
                answer = mid;
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        
        
        return answer;
    }
}