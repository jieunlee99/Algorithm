
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<String> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            queue.offer(br.readLine());
        }

        int answer = 0;
        for(int i=0; i<N; i++) {
            String out = br.readLine();

            if(!out.equals(queue.peek())) {
                answer++;
                queue.remove(out);
            } else {
                queue.poll();
            }
        }

        System.out.println(answer);
    }
}
