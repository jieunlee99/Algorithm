import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P2504/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack = new Stack<>();

        int result = 0;
        int value = 1;

        String input = br.readLine();

        for(int i=0; i<input.length(); i++) {
            char ch = input.charAt(i);

            if(ch == '(') {
                stack.push(ch);
                value *= 2;
            } else if(ch == '[') {
                stack.push(ch);
                value *= 3;
            } else if(ch == ')') {
                if(stack.isEmpty() || stack.peek() != '(') {
                    result = 0;
                    break;
                } else if(input.charAt(i-1) == '(') {
                    result += value;
                }

                stack.pop();
                value/=2;
            } else if(ch == ']') {
                if(stack.isEmpty() || stack.peek() != '[') {
                    result = 0;
                    break;
                } else if(input.charAt(i-1) == '[') {
                    result += value;
                }

                stack.pop();
                value/=3;
            }
        }

        if(!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }
}
