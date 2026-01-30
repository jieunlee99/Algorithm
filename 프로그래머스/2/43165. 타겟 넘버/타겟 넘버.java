class Solution {
    int[] numbers;
    int target;
    int n;
    
    int cnt = 0;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        this.n = numbers.length;
        
        dfs(0, 0);
        
        return cnt;
    }
    
    public void dfs(int current, int depth) {
        if(depth == n) {
            if(current == target) {
                cnt++;
            }
            return;
        }
        
        dfs(current+numbers[depth], depth+1);
        dfs(current-numbers[depth], depth+1);
    }
}