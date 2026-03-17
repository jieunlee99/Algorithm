import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Node>[] adjList;
    static boolean[] visited;
    static long totalCost = 0; 

    static class Node implements Comparable<Node> {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                
                if (i == j) continue;
                
                adjList[i].add(new Node(j, cost));
            }
        }

        prim(0);

        System.out.println(totalCost);
    }

    static void prim(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        int depth = 0; 
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.v]) continue;

            visited[current.v] = true;
            totalCost += current.cost;
            depth++;
            
            if(depth == N) break;

            for (Node next : adjList[current.v]) {
                if (!visited[next.v]) {
                    pq.offer(next);
                }
            }
        }
    }
}