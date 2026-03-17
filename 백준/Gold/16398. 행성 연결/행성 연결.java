import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] parent;
    static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        edges = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new Edge(i, j, cost));
            }
        }

        Collections.sort(edges);

        int depth = 1;
        long totalCost = 0;

        for (Edge edge : edges) {
            if (union(edge.u, edge.v)) {
                totalCost += edge.cost;
                depth++;

                if (depth == N) {
                    break;
                }
            }
        }

        System.out.println(totalCost);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot != yRoot) {
            parent[yRoot] = xRoot;
            return true;
        }

        return false;
    }

    static class Edge implements Comparable<Edge> {
        int u, v, cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
