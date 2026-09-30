import java.util.*;

class Solution {
    
    int answer = Integer.MAX_VALUE;

    boolean[] visited;
    int n;
    
    String target;
    String[] words;
    
    public int solution(String begin, String target, String[] words) {
        this.target = target;
        this.words = words;
        
        n = words.length;
        visited = new boolean[n];
        
        dfs(begin, 0);

        if(answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
    
    public void dfs(String current, int depth) {
        if(current.equals(target)) {
            answer = Math.min(depth, answer);
        }
        
        if(depth == n) {
            return;
        }
        
        for(int i=0; i<n; i++) {
            if(!visited[i] && canConvert(current, words[i])) {
                visited[i] = true;
                dfs(words[i], depth+1);
                visited[i] = false;
            }
        }
    }
    
    // a -> b 가능한지 확인
    public boolean canConvert(String a, String b) {
        int cnt = 0;
        for(int i=0; i<a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                cnt++;
                
                if(cnt >= 2) return false;
            }
        }
        
        return true;
    }
}