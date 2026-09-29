import java.util.*;

class Solution {
    
    List<String> list;
    char[] alpha = {'A', 'E', 'I', 'O', 'U'};
    
    public int solution(String word) {
        list = new ArrayList<>();
        
        dfs(0, "");
        
        Collections.sort(list);
        
        return list.indexOf(word);
    }
    
    public void dfs(int depth, String word) {
        if(!list.contains(word)) {
            list.add(word);
        }
        
        if(depth == 5) {
            return;
        }
        
        for(int i=0; i<5; i++) {
            dfs(depth+1, word+alpha[i]);
        }
    }
}