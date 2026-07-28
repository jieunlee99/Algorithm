import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int[] road:roads) {
            adjList[road[0]].add(road[1]);
            adjList[road[1]].add(road[0]);
        }
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        
        // 양방향 그래프 -> 역주행
        queue.offer(destination);
        dist[destination] = 0;
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            
            for(int next: adjList[current]) {
                if(dist[next] != -1) continue;
                
                dist[next] = dist[current] + 1;
                queue.offer(next);
            }
        }
        
        int[] answer = new int[sources.length];
        for(int i=0; i<sources.length; i++) {
            answer[i] = dist[sources[i]];
        }
        return answer;
    }
}