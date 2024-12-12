import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String vowels = "aeiouAEIOU";

        String input;
        while (!(input = br.readLine()).equals("#")) {
            int cnt = 0;

            for (char c : input.toCharArray()) {
                if (vowels.indexOf(c) != -1) {
                    cnt++;
                }
            }

            System.out.println(cnt);
        }
    }
}
