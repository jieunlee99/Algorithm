import java.io.*;
import java.util.*;

public class Main {

    static int N, M, R;
    static ArrayList<Integer>[] adjList;
    static int[] dp;
    static int cnt = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        dp = new int[N + 1];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
            adjList[v].add(u);
        }

        dfs(R);

        for(int i=1; i<=N; i++) {
            sb.append(dp[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int current) {
        dp[current] = cnt++;

        Collections.sort(adjList[current], Collections.reverseOrder());
        for(int next : adjList[current]) {
            if(dp[next] == 0) {
                dfs(next);
            }
        }
    }
}
