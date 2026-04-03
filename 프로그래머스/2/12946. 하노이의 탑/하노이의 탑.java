class Solution {
    int[][] answer;
    int idx = 0;
    
    public int[][] solution(int n) {
        answer = new int[(1<<n)- 1][2];
        hanoi(n, 1, 2, 3);
        return answer;
    }
    
    public void hanoi(int n, int from, int mid, int to) {
        if(n == 1)  {
            answer[idx][0] = from;
            answer[idx++][1] = to;
            return;
        }
        
        // 1번 -> 3번
        
        hanoi(n-1, from, to, mid); // from번의 n-1개를 mid번으로 옮김
        
        // from번의 1개를 to로 옮김
        answer[idx][0] = from; 
        answer[idx++][1] = to;
        
        hanoi(n-1, mid, from, to); // mid번의 n-1개를 to로 옮김
    }
}