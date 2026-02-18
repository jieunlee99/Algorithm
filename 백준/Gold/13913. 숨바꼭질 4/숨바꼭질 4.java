
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static int[] parent = new int[100_001];
    static int[] time = new int[100_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        sb.append(time[K]).append("\n");

        Stack<Integer> path = new Stack<>();
        int temp = K;
        while (temp != -1) {
            path.push(temp);
            temp = parent[temp];
        }

        while (!path.isEmpty()) {
            sb.append(path.pop()).append(" ");
        }

        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();

        Arrays.fill(parent, -1);
        Arrays.fill(time, -1);

        time[N] = 0;
        queue.offer(N);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == K) {
                return;
            }

            int[] nextPos = {current - 1, current + 1, current * 2};
            for (int next : nextPos) {
                if (next >= 0 && next <= 100_000 && time[next] == -1) {
                    time[next] = time[current] + 1;
                    parent[next] = current;
                    queue.offer(next);
                }
            }

        }
    }

}
