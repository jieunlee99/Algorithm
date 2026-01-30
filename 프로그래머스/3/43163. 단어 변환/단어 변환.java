import java.util.*;

class Solution {
    
    boolean[] visited;
    
    class Info {
        String word; // 현재 단어
        int cnt; // 변환 횟수

        public Info(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = Integer.MAX_VALUE;
        
        int n = words.length;
        visited = new boolean[n];
        
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(begin, 0));
        
        while(!queue.isEmpty()) {
            Info current = queue.poll();
            
            if(current.word.equals(target)) {
                answer = Math.min(answer, current.cnt);
            }
            
            for(int i=0; i<n; i++) {
                if(!visited[i] && canChange(current.word, words[i])) {
                    visited[i] = true;
                    queue.offer(new Info(words[i], current.cnt+1));
                }
            }
        }
        
        if(answer == Integer.MAX_VALUE) {
            return 0;
        }
        return answer;
    }
    
    public boolean canChange(String from, String to) {
        int diff = 0;
        
        int len = from.length();
        for(int i=0; i<len; i++) {
            if(from.charAt(i) != to.charAt(i)) {
                diff++;
                if(diff > 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
}