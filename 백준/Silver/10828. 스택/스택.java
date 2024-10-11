import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P10828/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();


        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("push")) {
                int x = Integer.parseInt(st.nextToken());
                stack.push(x);
            } else if (cmd.equals("pop")) {
                if (stack.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(stack.pop()).append("\n");
                }
            } else if (cmd.equals("size")) {
                sb.append(stack.size()).append("\n");
            } else if (cmd.equals("empty")) {
                if (stack.isEmpty()) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if (cmd.equals("top")) {
                if (stack.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(stack.peek()).append("\n");
                }
            }
        }

        System.out.print(sb);
    }

}
