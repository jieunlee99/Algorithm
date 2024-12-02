import java.io.*;
import java.util.*;

public class Main {

    static int K, V, E;
    static ArrayList<Integer>[] adjList;
    static int[] color;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // 초기화
            adjList = new ArrayList[V + 1];
            color = new int[V + 1]; // 0: 방문 안 함, 1: 색상 1, 2: 색상 2
            Arrays.fill(color, 0);

            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adjList[u].add(v);
                adjList[v].add(u);
            }

            sb.append(isBipartite() ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }

    static boolean isBipartite() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= V; i++) {
            if (color[i] == 0) { // 방문하지 않은 정점
                queue.add(i);
                color[i] = 1; // 첫 번째 색으로 칠함

                while (!queue.isEmpty()) {
                    int current = queue.poll();

                    for (int next : adjList[current]) {
                        if (color[next] == 0) { // 아직 방문하지 않은 경우
                            color[next] = 3 - color[current]; // 다른 색으로 칠함
                            queue.add(next);
                        } else if (color[next] == color[current]) {
                            // 인접한 정점이 같은 색이면 이분 그래프 아님
                            return false;
                        }
                    }
                }
            }
        }

        return true; // 모든 연결 요소가 이분 그래프 조건 만족
    }
}
