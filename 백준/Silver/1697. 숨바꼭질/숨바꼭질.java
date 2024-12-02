import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 100_000;
    static int N, K;
    static boolean[] visited = new boolean[MAX + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 시작 위치
        K = Integer.parseInt(st.nextToken()); // 목표 위치

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = true;

        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int pos = queue.poll();

                if (pos == K) {
                    return time;
                }

                int[] nextArr = { pos + 1, pos - 1, pos * 2 };
                for (int next : nextArr) {
                    if (isInRange(next) && !visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }
            time++;
        }

        return -1; 
    }

    static boolean isInRange(int num) {
        return 0 <= num && num <= MAX;
    }
}
