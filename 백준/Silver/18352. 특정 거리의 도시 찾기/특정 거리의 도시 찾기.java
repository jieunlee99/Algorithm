
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. 빠른 입력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // 2. 가중치가 1이므로 Edge 클래스 없이 Integer 리스트로 충분함 (메모리 절약)
        List<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
        }

        // 3. 거리 배열을 -1로 초기화 (방문 여부를 동시에 확인 가능)
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);

        // 4. BFS 시작
        Queue<Integer> q = new ArrayDeque<>(); // LinkedList보다 ArrayDeque가 약간 더 빠름
        q.offer(X);
        dist[X] = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();

            // 현재 거리가 K를 넘어가면 그 너머는 탐색할 필요 없음 (가지치기)
            if (dist[curr] >= K) continue;

            for (int next : adj[curr]) {
                if (dist[next] == -1) { // 방문하지 않은 도시만
                    dist[next] = dist[curr] + 1;
                    q.offer(next);
                }
            }
        }

        // 5. 결과 출력 (StringBuilder 활용)
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                sb.append(i).append("\n");
            }
        }

        if (sb.length() == 0) System.out.println(-1);
        else System.out.print(sb);
    }
}