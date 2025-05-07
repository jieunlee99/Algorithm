import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String input = br.readLine();
            sb.append(keylogger(input)).append("\n");
        }

        System.out.print(sb.toString());
    }

    static String keylogger(String input) {
        Deque<Character> left = new LinkedList<>();
        Deque<Character> right = new LinkedList<>();

        for (char c : input.toCharArray()) {
            if (c == '<') {
                if (!left.isEmpty()) {
                    right.push(left.removeLast());
                }
            } else if (c == '>') {
                if (!right.isEmpty()) {
                    left.addLast(right.pop());
                }
            } else if (c == '-') {
                if (!left.isEmpty()) {
                    left.removeLast();
                }
            } else {
                left.addLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : left) sb.append(c);
        while (!right.isEmpty()) sb.append(right.removeFirst()); // 🔧 여기 수정

        return sb.toString();
    }
}
