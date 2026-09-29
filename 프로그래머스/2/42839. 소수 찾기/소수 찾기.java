import java.util.*;

class Solution {
    
    Set<Integer> set = new HashSet<>(); 
    
    String[] arr;
    boolean[] visited;
    int n;
    
    public int solution(String numbers) {
        
        arr = numbers.split("");
        n = arr.length;
        
        visited = new boolean[n];
        
        dfs(0, "");
        
        return set.size();
    }
    
    public void dfs(int depth, String current) {
        if(!current.equals("")) {
            int num = Integer.parseInt(current);
        
            if(!set.contains(num) && isPrime(num)) {
                set.add(num);
                System.out.println(num);
            }
        }

        if(depth == n) {
            return;
        }
        
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(depth+1, current+arr[i]);
                visited[i] = false;
            }
        }
    }
    
    public boolean isPrime(int n) {
        if(n < 2) {
            return false;
        }
        
        for(int i=2; i<=Math.sqrt(n); i++) {
            if(n%i == 0) {
                return false;
            }
        }
        return true;
    }
}