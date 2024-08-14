import java.io.FileInputStream;
import java.util.*;

public class Main {
    static final long MAX = 1000000000;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P3425/input.txt"));
        Scanner sc = new Scanner(System.in);

        while (true) {
            List<String> commands = new ArrayList<>();

            while (true) {
                String command = sc.next();
                if (command.equals("END")) break;
                if (command.equals("QUIT")) return;

                commands.add(command);
                if (command.equals("NUM")) {
                    int num = sc.nextInt();
                    commands.add(String.valueOf(num));
                }
            }

            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                int value = sc.nextInt();
                String result = executeProgram(commands, value);
                System.out.println(result);
            }
            System.out.println();
        }
    }

    static String executeProgram(List<String> commands, int value) {
        Stack<Long> stack = new Stack<>();
        stack.push((long) value);

        try {
            for (int i = 0; i < commands.size(); i++) {
                String command = commands.get(i);

                switch (command) {
                    case "NUM":
                        long num = Long.parseLong(commands.get(++i));
                        stack.push(num);
                        break;
                    case "POP":
                        if (stack.isEmpty()) return "ERROR";
                        stack.pop();
                        break;
                    case "INV":
                        if (stack.isEmpty()) return "ERROR";
                        stack.push(-stack.pop());
                        break;
                    case "DUP":
                        if (stack.isEmpty()) return "ERROR";
                        stack.push(stack.peek());
                        break;
                    case "SWP":
                        if (stack.size() < 2) return "ERROR";
                        long first = stack.pop();
                        long second = stack.pop();
                        stack.push(first);
                        stack.push(second);
                        break;
                    case "ADD":
                        if (stack.size() < 2) return "ERROR";
                        long a = stack.pop();
                        long b = stack.pop();
                        long sum = a + b;
                        if (Math.abs(sum) > MAX) return "ERROR";
                        stack.push(sum);
                        break;
                    case "SUB":
                        if (stack.size() < 2) return "ERROR";
                        long a_sub = stack.pop();
                        long b_sub = stack.pop();
                        long sub = b_sub - a_sub;
                        if (Math.abs(sub) > MAX) return "ERROR";
                        stack.push(sub);
                        break;
                    case "MUL":
                        if (stack.size() < 2) return "ERROR";
                        long a_mul = stack.pop();
                        long b_mul = stack.pop();
                        long mul = a_mul * b_mul;
                        if (Math.abs(mul) > MAX) return "ERROR";
                        stack.push(mul);
                        break;
                    case "DIV":
                        if (stack.size() < 2) return "ERROR";
                        long a_div = stack.pop();
                        long b_div = stack.pop();
                        if (a_div == 0) return "ERROR";
                        long div = Math.abs(b_div) / Math.abs(a_div);
                        if (b_div < 0) div = -div;
                        if (a_div < 0) div = -div;
                        stack.push(div);
                        break;
                    case "MOD":
                        if (stack.size() < 2) return "ERROR";
                        long a_mod = stack.pop();
                        long b_mod = stack.pop();
                        if (a_mod == 0) return "ERROR";
                        long mod = Math.abs(b_mod) % Math.abs(a_mod);
                        if (b_mod < 0) mod = -mod;
                        stack.push(mod);
                        break;
                    default:
                        return "ERROR";
                }
            }
        } catch (Exception e) {
            return "ERROR";
        }
        
        if (stack.size() != 1) return "ERROR";

        return stack.pop().toString();
    }
}
