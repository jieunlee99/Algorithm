import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int K = 16;
    static int N, M;
    static boolean[] visited;
    static int[] depth;
    static int[][] parent;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        depth = new int[N + 1];
        visited = new boolean[N + 1];
        parent = new int[K + 1][N + 1];

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        bfs(1); // 루트는 1번
        initSparseTree(); // 족보 완성

        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append("\n");
        }

        System.out.print(sb);
    }

    static int lca(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // b를 a와 같은 깊이에 있도록 올려줌
        for (int k = K; k >= 0; k--) {
            if ((depth[b] - depth[a]) >= (1 << k)) {
                b = parent[k][b];
            }
        }

        // 같다면 바로 리턴
        if (a == b) {
            return a;
        }

        // 둘이 같아지기 직전까지 올려줌
        for (int k = K; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        // 같아지기 직전의 부모가 lca가 됨
        return parent[0][a];
    }

    static void initSparseTree() {
        for (int k = 1; k <= K; k++) {
            for (int v = 1; v <= N; v++) {
                parent[k][v] = parent[k - 1][parent[k - 1][v]];
            }
        }
    }

    static void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();

        depth[root] = 0;
        visited[root] = true;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : adjList[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    depth[next] = depth[current] + 1;
                    parent[0][next] = current;
                    queue.offer(next);
                }
            }
        }
    }
}
