import java.util.*;

class Solution {
    
    String[][] tickets;
    int n;
    boolean[] visited;
    ArrayList<String> list;
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        this.n = tickets.length;
        
        list = new ArrayList<>();
        visited = new boolean[n];
        
        dfs("ICN", "ICN", 0);
        
        // 가능한 경로가 여러 개 일 경우, 사전순으로 경로 return
        Collections.sort(list); 
        return list.get(0).split(" ");
    }
    
    public void dfs(String start, String path, int depth) {
        if(depth == n) {
            list.add(path);
            return;
        }
        
        for(int i=0; i<n; i++) {
            if(tickets[i][0].equals(start) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], path+" "+tickets[i][1], depth+1);
                visited[i] = false;
             }
        }
    }
}