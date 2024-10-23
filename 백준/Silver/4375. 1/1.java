
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null) {
            int n = Integer.parseInt(input);

            long num = 1;
            int cnt = 1;

            while (true) {
                if (num % n == 0) {
                    sb.append(cnt).append("\n");
                    break;
                }

                num = (num * 10 + 1) % n;
                cnt++;
            }
        }

        System.out.print(sb);
    }
}
