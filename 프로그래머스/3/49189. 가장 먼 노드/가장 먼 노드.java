import java.util.*;

class Solution {
    List<Integer>[] adjList;
    int n;
    
    public int solution(int n, int[][] edge) {
        this.n = n;
        
        // 1. 인접 리스트 초기화 및 구성
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            adjList[e[0]].add(e[1]);
            adjList[e[1]].add(e[0]);
        }
        
        // 2. BFS 실행: 1번 노드로부터 모든 노드까지의 최단 거리를 구함
        // distance[i] = 1번 노드로부터 i번 노드까지의 최단 거리
        int[] distance = new int[n + 1]; 
        Arrays.fill(distance, -1); // -1로 초기화 (아직 방문하지 않음/도달 불가)
        
        Queue<Integer> queue = new LinkedList<>();
        
        distance[1] = 0; // 1번 노드 자신과의 거리는 0
        queue.offer(1);
        
        int maxDistance = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            // 현재 노드의 거리가 최대 거리보다 크면 갱신
            if (distance[current] > maxDistance) {
                maxDistance = distance[current];
            }
            
            for (int next : adjList[current]) {
                // 아직 방문하지 않은 (거리가 -1인) 노드인 경우
                if (distance[next] == -1) { 
                    distance[next] = distance[current] + 1;
                    queue.offer(next);
                }
            }
        }
        
        // 3. 최대 거리와 같은 노드의 개수를 셈
        int count = 0;
        for (int dist : distance) {
            if (dist == maxDistance) {
                count++;
            }
        }
        
        return count;
    }
}