import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static int[] colors; // 0: 방문 안함, 1: 빨강, -1: 파랑

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            colors = new int[V + 1];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            boolean binary = true;
            for (int i = 1; i <= V; i++) {
                if (colors[i] == 0) {
                    if (!bfs(i)) {
                        binary = false;
                        break;
                    }
                }
            }

            sb.append(binary ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }

    public static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        colors[start] = 1; // 시작 정점 빨간색으로
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (colors[next] == 0) {
                    colors[next] = colors[current] * -1; // 현재 색상의 반대로 색칠
                    queue.offer(next);
                } else if (colors[next] == colors[current]) {
                    return false;
                }
            }
        }

        return true;
    }
}
