import java.io.*;
import java.util.*;

public class Main {

    static int L, R, C;
    static char[][][] map;
    static int[][][] dist;

    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                System.out.print(sb);
                return;
            }

            map = new char[L][R][C];
            dist = new int[L][R][C];

            int sx = -1, sy = -1, sz = -1;
            int ex = -1, ey = -1, ez = -1;

            for (int x = 0; x < L; x++) {
                for (int y = 0; y < R; y++) {
                    map[x][y] = br.readLine().toCharArray();

                    for (int z = 0; z < C; z++) {
                        if (map[x][y][z] == 'S') {
                            sx = x;
                            sy = y;
                            sz = z;
                        } else if (map[x][y][z] == 'E') {
                            ex = x;
                            ey = y;
                            ez = z;
                        }
                    }
                }
                br.readLine(); // 층 간 개행 처리
            }

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{sx, sy, sz});
            dist[sx][sy][sz] = 1; // 시작 지점을 1로 설정 (0과 구분)

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int cx = current[0];
                int cy = current[1];
                int cz = current[2];

                if (cx == ex && cy == ey && cz == ez) {
                    sb.append("Escaped in ").append(dist[cx][cy][cz] - 1).append(" minute(s).\n");
                    break;
                }

                for (int i = 0; i < 6; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    int nz = cz + dz[i];

                    if (isInRange(nx, ny, nz) && map[nx][ny][nz] != '#' && dist[nx][ny][nz] == 0) {
                        queue.offer(new int[]{nx, ny, nz});
                        dist[nx][ny][nz] = dist[cx][cy][cz] + 1;
                    }
                }
            }

            if (dist[ex][ey][ez] == 0) {
                sb.append("Trapped!\n");
            }
        }
    }

    static boolean isInRange(int x, int y, int z) {
        return 0 <= x && x < L && 0 <= y && y < R && 0 <= z && z < C;
    }
}
