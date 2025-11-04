import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times); // 빠른 순 정렬
        
        long left = 1; // 최소 1명, 최소 1분일 때
        long right = (long)times[times.length-1]*n; // 모든 사람이 제일 오래 기다리는 줄에 섰을 때
        
        long answer = right;
        
        while(left<=right) {
            long sum = 0;
            long mid = (left+right)/2;
            
            for(int time:times) {
                sum += mid/time;
            }
            
            if(sum >= n) { // n명이 1분 이상 기다려야 하기 때문
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        
        return answer;
    }
}