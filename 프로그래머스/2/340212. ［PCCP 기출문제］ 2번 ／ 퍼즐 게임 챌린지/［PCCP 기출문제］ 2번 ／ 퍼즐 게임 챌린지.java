class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int minLevel = 100_000; // 최대 난이도
        
        int left = 1; 
        int right = 100_000; // diff의 최대값
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(isPossible(diffs, times, limit, mid)) {
                minLevel = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return minLevel;
    }
    
    private boolean isPossible(int[] diffs, int[] times, long limit, int level) {
        long totalTime = 0;
        int n = diffs.length;
        
        for(int i = 0; i < n; i++) {
            int diff = diffs[i]; 
            int time_cur = times[i];
            int time_prev = (i == 0) ? 0 : times[i - 1]; 
            
            if(diff <= level) {
                totalTime += time_cur;
            } else {
                long count = diff - level;
                totalTime += (count * (time_cur + time_prev)) + time_cur;
            }
            
            if(totalTime > limit) {
                return false;
            }
        }
        
        return true;
    }
}