import java.util.*;

class Solution {
    
    List<Integer>[] list;
    Set<Integer> set;
    
    int n, m;
    
    public int solution(String[] user_id, String[] banned_id) {
        
        n = banned_id.length;
        m = user_id.length;
        
        list = new ArrayList[n];
        set = new HashSet<>();
        
        for(int i=0; i<n; i++) {
            list[i] = new ArrayList<>();
            
            for(int j=0; j<m; j++) {
                if(isMatch(user_id[j], banned_id[i])) {
                    list[i].add(j);
                }
            }
        }
        
        dfs(0, 0);
        
        return set.size();
    }
    
    private boolean isMatch(String user, String ban) {
        if(user.length() != ban.length()) return false;
        
        for(int i=0; i<user.length(); i++) {
            if(ban.charAt(i) != '*'
              && ban.charAt(i) != user.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
    
    private void dfs(int depth, int mask) {
        if(depth == n) {
            set.add(mask);
            return;
        }
        
        for(int num:list[depth]) {
            if((mask & (1 << num)) == 0) {
                dfs(depth+1, mask | (1 << num));
            }
        }
    }
}