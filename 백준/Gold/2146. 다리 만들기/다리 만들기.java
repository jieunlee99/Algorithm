import java.io.*;
import java.util.*;

public class Main {

    static int N;
    
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y, dist;

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        // 0. map 입력 받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 섬마다 번호 붙이기 (2번부터 시작)
        int islandNum = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    labelIsland(i, j, islandNum++);
                }
            }
        }

        // 2. 각 섬의 가장자리에서 다른 섬까지의 최소 거리 찾기
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2) {
                    answer = Math.min(answer, bfsToOtherIsland(i, j, map[i][j]));
                }
            }
        }

        System.out.println(answer);
    }

    static void labelIsland(int x, int y, int islandNum) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 0));
        visited[x][y] = true;
        map[x][y] = islandNum;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (inRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    map[nx][ny] = islandNum;
                    q.add(new Point(nx, ny, 0));
                }
            }
        }
    }

    static int bfsToOtherIsland(int x, int y, int islandNum) {
        boolean[][] visitedBridge = new boolean[N][N];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 0));
        visitedBridge[x][y] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (!inRange(nx, ny) || visitedBridge[nx][ny]) continue;

                if (map[nx][ny] == 0) {
                    visitedBridge[nx][ny] = true;
                    q.add(new Point(nx, ny, cur.dist + 1));
                } else if (map[nx][ny] != islandNum) {
                    return cur.dist;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
