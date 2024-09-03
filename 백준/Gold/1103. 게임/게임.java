import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] dp;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P1103/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];
        dp = new int[N][M];

        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = input.charAt(m);
            }
        }

        int result = dfs(0, 0);
        if (result == -1) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    public static int dfs(int y, int x) {
        if (y < 0 || x < 0 || y >= N || x >= M || map[y][x] == 'H') {
            return 0;
        }

        if (visited[y][x]) {
            return -1;  // 무한 루프 탐지
        }

        if (dp[y][x] != 0) {
            return dp[y][x];
        }

        visited[y][x] = true;
        int maxMove = 0;
        int move = map[y][x] - '0';  // 숫자로 변환

        for (int i = 0; i < 4; i++) {
            int ty = y + dy[i] * move;
            int tx = x + dx[i] * move;

            int result = dfs(ty, tx);
            if (result == -1) {
                return -1;  // 무한 루프 발생 시 -1 반환
            }
            maxMove = Math.max(maxMove, result + 1);
        }

        visited[y][x] = false;
        dp[y][x] = maxMove;
        return dp[y][x];
    }
}
