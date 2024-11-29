import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String bomb = br.readLine();

        int bombLength = bomb.length();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {

            // 폭탄이 터지는 문자의 수와 같아질 때 pop 시키기 위함
            int cnt = 0;

            stack.push(input.charAt(i));

            if (stack.size() >= bombLength) {
                for (int j = 0; j < bombLength; j++) {
                    if (stack.get(stack.size() - bombLength + j) == bomb.charAt(j)) {
                        cnt++;
                    }

                    if (cnt == bombLength) {
                        for (int k = 0; k < bombLength; k++) {
                            stack.pop();
                        }
                    }
                }
            }
        }
 
        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) {
            // 남아있는게 없을 때 "FRULA" 출력
            sb.append("FRULA");
        } else {
            for (char c : stack) {
                sb.append(c);
            }
        }

        System.out.println(sb.toString());
    }
}
