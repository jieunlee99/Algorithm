import java.util.*;

class Solution {
    
    List<Integer>[] adjList;
    
    public int solution(int n, int[][] edge) {
        
        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int[] e:edge) {
            adjList[e[0]].add(e[1]);
            adjList[e[1]].add(e[0]);
        }
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1); // -1 (미방문)
        
        Queue<Integer> queue = new LinkedList<>();
        
        dist[1] = 0;
        queue.offer(1);
        
        int maxDist = 0;
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            
            // 최댓값 갱신
            if(maxDist < dist[current]) {
                maxDist = dist[current];
            }
            
            for(int next:adjList[current]) {
                if(dist[next] == -1) {
                    dist[next] = dist[current]+1;
                    queue.offer(next);
                }
            }
        }
        
        int answer = 0;
        for(int d:dist) {
            if(d == maxDist) {
                answer++;
            }
        }
        return answer;
    }
}