import java.util.*;

class Solution {
    
    List<Node>[] adjList;
    int[] dist;
    
    class Node implements Comparable<Node> {
        int to, cost;
        
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);  
        dist[1] = 0;
        
        adjList = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int[] r:road) {
            int u = r[0];
            int v = r[1];
            int cost = r[2];
            
            adjList[u].add(new Node(v, cost));
            adjList[v].add(new Node(u, cost));
        }
        
        dijkstra();
        
        for(int i=1; i<=N; i++) {
            if(dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
    
    public void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0)); // 1부터 시작
        
        while(!pq.isEmpty()) {
            Node current = pq.poll();
            
            if(current.cost > dist[current.to]) {
                continue;
            }
            
            for(Node next:adjList[current.to]) {
                if(dist[next.to] > dist[current.to] + next.cost) {
                    dist[next.to] = dist[current.to] + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
        
    }
}