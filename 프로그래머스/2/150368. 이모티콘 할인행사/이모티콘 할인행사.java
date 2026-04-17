class Solution {
    
    int[] discount = {10, 20, 30, 40};
    
    int[][] users;
    int[] emoticons;
    int[] selectedDiscount;
    int n;
    
    int[] answer = {0, 0};
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        this.n = emoticons.length;
        this.users = users;
        this.emoticons = emoticons;
        
        selectedDiscount = new int[n];
        
        dfs(0);
        
        return answer;
    }
    
    private void calculate() {
        int join = 0;
        int revenue = 0;
        
        for(int[] user:users) {
            int userRequiredDiscount = user[0];
            int userBudget = user[1];
            int userSpent = 0;
            
            for(int i=0; i<n; i++) {
                if(selectedDiscount[i] >= userRequiredDiscount) {
                    userSpent += emoticons[i]*(100-selectedDiscount[i])/100;
                }
            }
            
            if(userSpent >= userBudget) {
                join++;
            } else {
                revenue += userSpent;
            }
        }
        
        if(join > answer[0]) {
            answer[0] = join;
            answer[1] = revenue;
        } else if(join == answer[0]) {
            answer[1] = Math.max(answer[1], revenue);
        }
    }
    
    private void dfs(int depth) {
        if(depth == n) {
            calculate();
            return;
        }
        
        for(int i=0; i<4; i++) {
            selectedDiscount[depth] = discount[i];
            dfs(depth+1);
        }
    }
}