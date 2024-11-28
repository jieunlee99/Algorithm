import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        // Push 하는 순서는 반드시 오름차순을 지킴.
        // lastNum을 기억해두었다가 그 다음 수부터 push 가능

        int lastNum = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > lastNum) {
                for (int j = lastNum + 1; j <= num; j++) {
                    stack.push(j);
                    sb.append("+").append("\n");
                }
                lastNum = num;
            }

            if (stack.get(stack.size() - 1) == num) {
                stack.pop();
                sb.append("-").append("\n");
            }
        }

        if (stack.size() > 0) {
            bw.write("NO\n");
        } else {
            bw.write(sb.toString());
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
