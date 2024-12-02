import java.io.*;
import java.util.*;

public class Main {

    static int T, I;
    static boolean[][] visited;
    static final int[] dx = { 2, 2, -2, -2, 1, 1, -1, -1 };
    static final int[] dy = { 1, -1, 1, -1, 2, -2, 2, -2 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            I = Integer.parseInt(br.readLine());

            visited = new boolean[I][I];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int fromX = Integer.parseInt(st.nextToken());
            int fromY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int toX = Integer.parseInt(st.nextToken());
            int toY = Integer.parseInt(st.nextToken());

            sb.append(bfs(fromX, fromY, toX, toY)).append("\n");
        }

        System.out.print(sb);
    }

    static int bfs(int fromX, int fromY, int toX, int toY) {
        if (fromX == toX && fromY == toY) return 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { fromX, fromY });
        visited[fromX][fromY] = true;

        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0], y = current[1];

                for (int d = 0; d < 8; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx == toX && ny == toY) {
                        return depth + 1; 
                    }

                    if (isInRange(nx, ny) && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[] { nx, ny });
                    }
                }
            }
            depth++;
        }
        return -1; 
    }

    static boolean isInRange(int x, int y) {
        return 0 <= x && x < I && 0 <= y && y < I;
    }
}
