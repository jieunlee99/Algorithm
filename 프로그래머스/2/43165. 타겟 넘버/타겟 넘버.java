class Solution {
    
    int[] numbers;
    int target;
    int n;
    
    int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        this.numbers = numbers;
        this.target = target;
        this.n = numbers.length;
        
        dfs(0, 0);
        
        return answer;
    }
    
    public void dfs(int depth, int sum) {
        if(depth == n && sum == target) {
            answer++;
            return;
        }
        
        if(depth < n) {
            dfs(depth+1, sum+numbers[depth]);
            dfs(depth+1, sum-numbers[depth]);
        }
        
    }
}