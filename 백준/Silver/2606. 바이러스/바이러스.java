import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];

        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
            adjList[v].add(u);
        }

        int cnt = 0;
        int start = 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : adjList[current]) {
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

}
