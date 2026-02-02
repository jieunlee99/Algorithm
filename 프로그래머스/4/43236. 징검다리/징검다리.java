import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        long left = 1;
        long right = distance;
        
        while(left <= right) {
            long mid = (left+right)/2;
            
            int removedCnt = 0; // 제거된 바위의 수
            int lastPosition = 0; // 마지막 바위의 위치
            
            for(int rock:rocks) {
                // 현재 바위와 이전 바위 사이의 거리가 mid보다 작으면 제거
                if(rock - lastPosition < mid) {
                    removedCnt++;
                } else {
                    // mid보다 크거나 같으면 조건 만족하므로 바위를 두고 기준점 이동
                    lastPosition = rock;
                }
            }
            
            // 마지막 바위와 도착 지점 사이의 거리도 체크
            if(distance - lastPosition < mid) {
                removedCnt++;
            }
            
            // n개 이하로 제거하여 최소 거리를 유지할 수 있다면
            // 더 큰 값으로도 가능한지 범위 이동
            if(removedCnt <= n) {
                answer = (int)mid;
                left = mid+1;
            } else {
                // 너무 많은 바위를 제거해야 한다면 mid를 줄여 범위 이동
                right = mid-1;
            }
        }
        
        return answer;
    }
}