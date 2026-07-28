class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int left = 0;
        int right = 200_000_000; // stones 최댓값
        
        while(left <= right) {
            int mid = left + (right - left) / 2; 
        
            if(isPossible(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    // 현재 stones를 최대 k 간격으로 건널 때 x명이 건너는게 가능한지?
    private boolean isPossible(int[] stones, int k, int x) {
        int cnt = 0;
        
        // stone가 x보다 작은 간격이 k개 이상이면 불가능
        for(int stone:stones) {
            
            if(stone < x) {
                cnt++;
            } else {
                cnt = 0;
            }
            
            if(cnt >= k) {
                return false;
            }
        }
        
        return true;
    } 
}