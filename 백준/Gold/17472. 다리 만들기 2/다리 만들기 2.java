import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] parent;
    static List<Node>[] islands;
    static List<Bridge> bridges;
    static final int[] dx = { 0, 0, -1, 1 };
    static final int[] dy = { 1, -1, 0, 0 };
    static int islandCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        islands = new ArrayList[7];
        bridges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        islandCount = findIslands();
        findBridges();
        initParent();

        int answer = kruskal();
        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }

    static int findIslands() {
        int islandNo = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    islands[++islandNo] = new ArrayList<>();
                    markIsland(i, j, islandNo);
                }
            }
        }
        return islandNo;
    }

    static void markIsland(int x, int y, int islandNo) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            map[current.x][current.y] = islandNo;
            islands[islandNo].add(current);

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (isInBounds(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.offer(new Node(nx, ny));
                }
            }
        }
    }

    static void findBridges() {
        for (int islandNo = 1; islandNo <= islandCount; islandNo++) {
            for (Node start : islands[islandNo]) {
                for (int i = 0; i < 4; i++) {
                    buildBridge(start, i, islandNo);
                }
            }
        }
    }

    static void buildBridge(Node start, int direction, int islandNo) {
        int length = 0;
        int nx = start.x + dx[direction];
        int ny = start.y + dy[direction];

        while (isInBounds(nx, ny)) {
            if (map[nx][ny] == islandNo) return; // 같은 섬에 도착
            if (map[nx][ny] > 0) { // 다른 섬에 도착
                if (length >= 2) { // 다리 길이가 2 이상인 경우에만 추가
                    bridges.add(new Bridge(islandNo, map[nx][ny], length));
                }
                return;
            }
            length++;
            nx += dx[direction];
            ny += dy[direction];
        }
    }

    static boolean isInBounds(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static int kruskal() {
        int mstCost = 0;
        int edgesUsed = 0;
        Collections.sort(bridges);

        for (Bridge bridge : bridges) {
            if (union(bridge.from, bridge.to)) {
                mstCost += bridge.length;
                edgesUsed++;
                if (edgesUsed == islandCount - 1) {
                    return mstCost;
                }
            }
        }
        return -1; // 모든 섬을 연결할 수 없는 경우
    }

    static void initParent() {
        parent = new int[islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            parent[i] = i;
        }
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
            return true;
        }
        return false;
    }
}

class Node {
    int x, y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Bridge implements Comparable<Bridge> {
    int from, to, length;

    Bridge(int from, int to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    @Override
    public int compareTo(Bridge o) {
        return Integer.compare(this.length, o.length);
    }
}
