

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P1927/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            int x = Integer.parseInt(br.readLine());

            if(x == 0) {
                if(!pq.isEmpty()) {
                    sb.append(pq.poll()).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else {
                pq.add(x);
            }
        }

        System.out.print(sb);
    }
}
