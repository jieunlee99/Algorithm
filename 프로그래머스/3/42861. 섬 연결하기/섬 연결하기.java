import java.util.*;

// 크루스칼(v) & 프림

class Solution {
    private int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        
        parent = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
    
        int selected = 0;
        for(int[] edge:costs) {
            if(selected == n-1) {
                break;
            }
            
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            
            if(find(u) != find(v)) {
                union(u, v);
                answer += cost;
                selected++;
            }
        }
        
        return answer;
    }
    
    public int find(int n) {
        if(parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }
    
    public void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot != bRoot)
            parent[bRoot] = aRoot;
    }
}