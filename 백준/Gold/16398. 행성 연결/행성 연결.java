import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        boolean[] visited = new boolean[N];
        long[] minEdge = new long[N];
        Arrays.fill(minEdge, Long.MAX_VALUE);
        
        minEdge[0] = 0;
        long result = 0;
        
        for(int i = 0; i < N; i++) {
            int minVertex = -1;
            long min = Long.MAX_VALUE;
            
            for(int j = 0; j < N; j++) {
                if(!visited[j] && minEdge[j] < min) {
                    min = minEdge[j];
                    minVertex = j;
                }
            }
            
            visited[minVertex] = true;
            result += min;
            
            for(int j = 0; j < N; j++) {
                if(!visited[j] && cost[minVertex][j] < minEdge[j]) {
                    minEdge[j] = cost[minVertex][j];
                }
            }
        }
        System.out.println(result);
    }
}