import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, L;
    static int[][] board;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 보드 크기
        board = new int[N][N];

        K = Integer.parseInt(br.readLine()); // 사과 개수
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            board[row][col] = 1; // 사과 표시
        }

        L = Integer.parseInt(br.readLine());

        Map<Integer, Character> dirMap = new HashMap<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            dirMap.put(X, C);
        }

        Deque<int[]> snake = new LinkedList<>();
        snake.offerFirst(new int[]{0, 0});
        board[0][0] = 2; // 머리 표시

        int time = 0;
        int dir = 0;
        int hx = 0, hy = 0;

        while (true) {
            time++;

            hx += dx[dir];
            hy += dy[dir];

            if (hx < 0 || hx >= N || hy < 0 || hy >= N || board[hx][hy] == 2) {
                System.out.println(time);
                break;
            }

            // 사과가 있다면 머리만 변경하면 됨
            if (board[hx][hy] == 1) {
                board[hx][hy] = 2;
                snake.offerFirst(new int[]{hx, hy});
            }

            // 사과가 없다면 꼬리 줄여주기
            else {
                board[hx][hy] = 2;
                snake.offerFirst(new int[]{hx, hy});

                int[] tail = snake.pollLast();
                board[tail[0]][tail[1]] = 0; // 크기가 그대로인 상태로 움직여서 꼬리가 바뀜
            }

            // 방향을 전환해야 하는 시간이면 전환함
            if (dirMap.containsKey(time)) {
                char turn = dirMap.get(time);
                if (turn == 'L') {
                    dir = (dir + 3) % 4;
                } else {
                    dir = (dir + 1) % 4;
                }
            }
        }


    }
}
