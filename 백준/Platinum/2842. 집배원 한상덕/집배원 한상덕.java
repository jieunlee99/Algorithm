import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, House, Min = Integer.MAX_VALUE;
    static Point start;
    static boolean[][] visit;
    static char[][] map;
    static int[][] arr;
    static ArrayList<Integer> arrList = new ArrayList<>();
    static int[] moveX = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] moveY = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char ch = str.charAt(j);
                if (ch == 'P')
                    start = new Point(j, i);
                if (ch == 'K')
                    House++;

                map[i][j] = ch;
            }
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                arr[i][j] = val;
                if (!arrList.contains(val))
                    arrList.add(val);
            }
        }
        Collections.sort(arrList);

        bfs();

    }

    public static void bfs() {
        int low = 0, high = 0;
        while (low < arrList.size()) {
            visit = new boolean[N][N];
            Queue<Point> queue = new LinkedList<>();
            int val = arr[start.y][start.x];
            if (arrList.get(low) <= val && val <= arrList.get(high)) {
                visit[start.y][start.x] = true;
                queue.add(new Point(start.x, start.y));
            }
            
            int count = 0;
            while (!queue.isEmpty()) {
                Point current = queue.poll();

                // 방문한 집 개수 세기
                if (map[current.y][current.x] == 'K') {
                    count++;
                }

                // 연결된 곳 순회
                for (int d = 0; d < 8; d++) {
                    int newY = current.y + moveY[d];
                    int newX = current.x + moveX[d];

                    // 범위 내에 있고 방문한 적이 없다면 go
                    if (0 <= newY && newY < N && 0 <= newX && newX < N && !visit[newY][newX]) {
                        int nextVal = arr[newY][newX];
                        // 다음 좌표의 고도가 범위 내에 있다면 go
                        if (arrList.get(low) <= nextVal && nextVal <= arrList.get(high)) {
                            visit[newY][newX] = true;
                            queue.add(new Point(newX, newY));
                        }

                    }
                }
            }

            // 모든 집에 방문할 수 있다면 최소값 업데이트
            if (House == count) {
                Min = Math.min(Min, arrList.get(high) - arrList.get(low));
                // 더 작은 범위에서 모든 집에 방문할 수 있는지 확인하기 위해 고도 범위의 하한선 증가시킨다.
                low++;
            } else if (high + 1 < arrList.size()) {
                // 모든 집을 방문할 수 없다면 고도 범위의 상한선을 증가시킨다.
                high++;
            } else {
                // 가능한 범위를 모두 탐색했을 때는 종료시킨다.
                break;
            }
        }

        System.out.println(Min);
    }
}