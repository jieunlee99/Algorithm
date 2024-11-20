import java.io.*;
import java.util.*;

public class Main {

    static int N, M, V;
    
    // dfs
    static boolean[] visited;
    static ArrayList<Integer>[] adjList;
    
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수
        V = Integer.parseInt(st.nextToken()); // 시작 정점

        visited = new boolean[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from); // 무방향 간선 추가
        }

        // 각 정점의 인접 리스트를 정렬하여 정점 번호 순서대로 탐색하도록 함
        for (int i = 1; i <= N; i++) {
            Collections.sort(adjList[i]);
        }
        
        // DFS 실행
        dfs(V);
        sb.append("\n");

        // BFS 실행을 위해 visited 배열 초기화
        Arrays.fill(visited, false);
        bfs(V);

        System.out.print(sb.toString());
    }

    // DFS 탐색
    static void dfs(int current) {
        visited[current] = true;
        sb.append(current).append(" ");
        
        // 인접 노드들에 대해 재귀적으로 DFS 수행
        for (int next : adjList[current]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    // BFS 탐색
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current).append(" ");
            
            // 인접 노드들에 대해 BFS 수행
            for (int next : adjList[current]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        
        sb.append("\n");
    }
}
