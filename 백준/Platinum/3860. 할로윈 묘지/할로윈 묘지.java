import java.util.*;
import java.io.*;

public class Main {
    static class Edge{
        Node from;
        Node to;
        int cost;

        public Edge(Node from, Node to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int W, H, G, E;
    static int[][] map;
    static final int[] dx = { 0, 0, -1, 1 };
    static final int[] dy = { 1, -1, 0, 0 };
    static int[][] dist;
    static ArrayList<Edge> edgeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if(W == 0 && H == 0){
                break;
            }

            map = new int[W][H];
            dist = new int[W][H];
            edgeList = new ArrayList<>();

            map[W-1][H-1] = 2;
    
            G = Integer.parseInt(br.readLine());
            int x, y;
            for(int g=0; g<G; g++){
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
    
                map[x][y] = -1;
            }
    
            E = Integer.parseInt(br.readLine());
            int x1, y1, x2, y2, t;
            for(int e=0; e<E; e++){
                st = new StringTokenizer(br.readLine());
                x1 = Integer.parseInt(st.nextToken());
                y1 = Integer.parseInt(st.nextToken());
                x2 = Integer.parseInt(st.nextToken());
                y2 = Integer.parseInt(st.nextToken());
                t = Integer.parseInt(st.nextToken());
    
                map[x1][y1] = 1;
                edgeList.add(new Edge(new Node(x1, y1), new Node(x2, y2), t));
            }
    
            for(int w=0; w<W; w++){
                for(int h=0; h<H; h++){
                    
                    if(map[w][h] != 0){
                        continue;
                    }
    
                    int nx, ny;
                    for(int i=0; i<4; i++){
                        nx = w + dx[i];
                        ny = h + dy[i];
    
                        if((nx >=0 && ny >=0 && nx < W && ny < H) && map[nx][ny] != -1){
                            edgeList.add(new Edge(new Node(w, h), new Node(nx, ny), 1));
                        }
                    }
                }
            }
    
            if(bellmanFordMoore(W*H)){
                sb.append("Never\n");
            }else{
                if (dist[W-1][H-1] == Integer.MAX_VALUE) {
                    sb.append("Impossible\n");
                } else {
                    sb.append(dist[W-1][H-1]).append("\n");
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    static boolean bellmanFordMoore(int V){
        for(int i=0; i<W; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        for(int i=0; i<V-1; i++){
            for(Edge edge : edgeList){
                Node start = edge.from;
                Node end = edge.to;

                if(dist[start.x][start.y] == Integer.MAX_VALUE){
                    continue;
                }

                if(dist[end.x][end.y] > dist[start.x][start.y] + edge.cost){
                    dist[end.x][end.y] = dist[start.x][start.y] + edge.cost;
                }
            }
        }


        boolean isNegativeCycle = false;

        for(Edge edge : edgeList){
            Node start = edge.from;
            Node end = edge.to;

            if(dist[start.x][start.y] == Integer.MAX_VALUE){
                continue;
            }

            if(dist[end.x][end.y] > dist[start.x][start.y] + edge.cost){
                isNegativeCycle = true;
                break;
            }
        }

        return isNegativeCycle;
    }
}
