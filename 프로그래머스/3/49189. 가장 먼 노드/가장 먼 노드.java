import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        
        int[] arr = new int[n+1]; // 1번 노드와의 거리 
        Arrays.fill(arr, n);
        
        List<Integer>[] adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
 
        for(int[] e:edge) {
            adjList[e[0]].add(e[1]);
            adjList[e[1]].add(e[0]);
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        arr[1] = 0;

        int max = 0;
        
        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int next:adjList[current]) {
                if(arr[next] > arr[current] + 1) {
                    queue.offer(next);
                    arr[next] = arr[current] + 1;
                    max = Math.max(max, arr[next]);
                }
            }
        }
        
        int cnt = 0;
        for(int i=2; i<=n; i++) {
            if(arr[i] == max) {
                cnt++;
            }
        }
        
        return cnt;
    }
}