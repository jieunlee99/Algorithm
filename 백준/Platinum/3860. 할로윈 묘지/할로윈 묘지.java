
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final long INF = Long.MAX_VALUE / 2;
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};
    static int W, H, G, X, Y, E, X1, X2, Y1, Y2, T;
    static int[][] map;
    static long[][] dist;
    static List<Edge> edgeList;

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge {
        Node from, to;
        int cost;

        public Edge(Node from, Node to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if (W == 0 && H == 0) {
                break;
            }

            map = new int[W][H];
            dist = new long[W][H];
            edgeList = new ArrayList<>();

            map[W - 1][H - 1] = 2; // 도착지

            G = Integer.parseInt(br.readLine());
            for (int i = 0; i < G; i++) {
                st = new StringTokenizer(br.readLine());
                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());
                map[X][Y] = -1; // 묘비
            }

            E = Integer.parseInt(br.readLine());
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                X1 = Integer.parseInt(st.nextToken());
                Y1 = Integer.parseInt(st.nextToken());
                X2 = Integer.parseInt(st.nextToken());
                Y2 = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());
                map[X1][Y1] = 1; // 귀신 구멍
                edgeList.add(new Edge(new Node(X1, Y1), new Node(X2, Y2), T));
            }

            for (int x = 0; x < W; x++) {
                for (int y = 0; y < H; y++) {

                    if (map[x][y] != 0) {
                        continue;
                    }

                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[nx][ny] != -1) {
                            edgeList.add(new Edge(new Node(x, y), new Node(nx, ny), 1));
                        }
                    }
                }
            }

            if (bellmanFord(W * H)) {
                sb.append("Never").append("\n"); // 음수 사이클 존재하면 Never
            } else {
                if (dist[W - 1][H - 1] == INF) {
                    sb.append("Impossible").append("\n"); // 출구에 갈 수 없음
                } else {
                    sb.append(dist[W - 1][H - 1]).append("\n"); // 출구에 갈 수 있음
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean bellmanFord(int V) {
        for (int i = 0; i < W; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = 0;

        // V-1번 반복 (정점 개수만큼)
        for (int i = 0; i < V - 1; i++) {
            boolean updated = false;
            for (Edge edge : edgeList) {
                if (dist[edge.from.x][edge.from.y] == INF) continue;

                if (dist[edge.to.x][edge.to.y] > dist[edge.from.x][edge.from.y] + edge.cost) {
                    dist[edge.to.x][edge.to.y] = dist[edge.from.x][edge.from.y] + edge.cost;
                    updated = true;
                }
            }
            if (!updated) break; // 더 이상 갱신되지 않으면 조기 종료 가능
        }

        // 음수 사이클 체크 (반드시 INF 체크 포함!)
        for (Edge edge : edgeList) {
            if (dist[edge.from.x][edge.from.y] == INF) continue;

            if (dist[edge.to.x][edge.to.y] > dist[edge.from.x][edge.from.y] + edge.cost) {
                return true;
            }
        }
        return false;
    }
}
