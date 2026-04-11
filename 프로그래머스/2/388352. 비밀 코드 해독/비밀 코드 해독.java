class Solution {

    int[] selected = new int[5];    
    int cnt = 0;
        
    public int solution(int n, int[][] q, int[] ans) {
        dfs(1, 0, n, q, ans);
        return cnt;
    }
    
    // 가능한 모든 비밀번호 생성 후 확인
    private void dfs(int start, int depth, int n, int[][] q, int[] ans) {
        if(depth == 5) {
            if(check(q, ans)) {
                cnt++;
            }
            return;
        }
        
        for(int i=start; i<=n; i++) {
            selected[depth] = i;
            dfs(i+1, depth+1, n, q, ans);
        }
    }
    
    // 현재 고른 비밀번호로 조건을 만족할 수 있는지 확인
    private boolean check(int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int matchCount = 0;

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (selected[j] == q[i][k]) {
                        matchCount++;
                        break; 
                    }
                }
            }

            if (matchCount != ans[i]) {
                return false;
            }
        }
        return true;
    }
}