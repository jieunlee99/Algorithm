import java.util.*;

class Solution {
    ArrayList<String> list = new ArrayList<>();
    
    public int solution(String word) {
        dfs("", 0);
        Collections.sort(list);
        return list.indexOf(word);
    }
    
    public void dfs(String current, int depth) {
        
        if(depth <= 5) {
            if(!list.contains(current)) {
                list.add(current);
            }
            if(depth == 5) return;
        }

        dfs(current+"A", depth+1);
        dfs(current+"E", depth+1);
        dfs(current+"I", depth+1);
        dfs(current+"O", depth+1);
        dfs(current+"U", depth+1);
    }
}