import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;

    static class Beads {
        int rx, ry, bx, by, cnt;

        public Beads(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        int answer = bfs(rx, ry, bx, by);
        System.out.println(answer);
    }

    public static int bfs(int rx, int ry, int bx, int by) {
        Queue<Beads> queue = new LinkedList<>();
        queue.offer(new Beads(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!queue.isEmpty()) {
            Beads current = queue.poll();

            if (current.cnt >= 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {

                // 1. 빨간 구슬을 벽까지 굴림

                int nrx = current.rx;
                int nry = current.ry;

                // 벽을 만나기 전 or 구멍이 아닐 때까지 이동
                int rDist = 0;
                while (map[nrx + dx[i]][nry + dy[i]] != '#' && map[nrx][nry] != 'O') {
                    nrx += dx[i];
                    nry += dy[i];
                    rDist++;
                }

                // 2. 파란 구슬을 벽까지 굴림

                int nbx = current.bx;
                int nby = current.by;

                int bDist = 0;
                while (map[nbx + dx[i]][nby + dy[i]] != '#' && map[nbx][nby] != 'O') {
                    nbx += dx[i];
                    nby += dy[i];
                    bDist++;
                }

                // 파란 구슬이 구멍에 빠지면 이 경우 제거, 다음 방향 ㄱㄱ
                if (map[nbx][nby] == 'O') {
                    continue;
                }

                // 빨간 구슬만 구멍에 빠진다면 성공
                if (map[nrx][nry] == 'O') {
                    return current.cnt + 1;
                }

                // 두 구슬의 위치가 겹친다면 더 뒤에 있던 구슬을 한 칸 뒤로 물림
                if (nrx == nbx && nry == nby) {
                    if (rDist > bDist) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                // 방문한 적 없다면 큐 삽입
                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.offer(new Beads(nrx, nry, nbx, nby, current.cnt + 1));
                }
            }
        }

        return -1;
    }
}