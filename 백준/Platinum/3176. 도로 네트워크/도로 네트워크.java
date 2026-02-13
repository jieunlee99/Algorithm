import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int LOG = 17;
    static int N, K;
    static int[] depth;
    static boolean[] visited;
    static int[][] parent, minDist, maxDist;
    static List<Road>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        depth = new int[N + 1];
        visited = new boolean[N + 1];
        parent = new int[LOG + 1][N + 1];
        minDist = new int[LOG + 1][N + 1];
        maxDist = new int[LOG + 1][N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            adjList[A].add(new Road(B, C));
            adjList[B].add(new Road(A, C));
        }

        bfs(1);
        initSparseTable();

        K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int[] result = lca(D, E);
            sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }

        System.out.print(sb);
    }

    static int[] lca(int a, int b) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int k = LOG; k >= 0; k--) {
            if ((depth[b] - depth[a]) >= (1 << k)) {
                min = Math.min(min, minDist[k][b]);
                max = Math.max(max, maxDist[k][b]);
                b = parent[k][b];
            }
        }

        if (a == b) {
            return new int[] {min, max};
        }

        for (int k = LOG; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                min = Math.min(min, Math.min(minDist[k][a], minDist[k][b]));
                max = Math.max(max, Math.max(maxDist[k][a], maxDist[k][b]));
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        min = Math.min(min, Math.min(minDist[0][a], minDist[0][b]));
        max = Math.max(max, Math.max(maxDist[0][a], maxDist[0][b]));

        return new int[]{min, max};
    }

    static void initSparseTable() {
        for (int k = 1; k <= LOG; k++) {
            for (int v = 1; v <= N; v++) {
                parent[k][v] = parent[k - 1][parent[k - 1][v]];

                minDist[k][v] = Math.min(minDist[k - 1][v], minDist[k - 1][parent[k - 1][v]]);
                maxDist[k][v] = Math.max(maxDist[k - 1][v], maxDist[k - 1][parent[k - 1][v]]);
            }
        }
    }

    static void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();

        visited[root] = true;
        depth[root] = 0;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Road road : adjList[current]) {
                int next = road.v;
                int dist = road.dist;

                if (!visited[next]) {
                    visited[next] = true;
                    depth[next] = depth[current] + 1;
                    parent[0][next] = current;
                    queue.offer(next);

                    // current->next 비용은 dist
                    minDist[0][next] = dist;
                    maxDist[0][next] = dist;
                }
            }
        }
    }

    static class Road {
        int v, dist;

        public Road(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }
}
