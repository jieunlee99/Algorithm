import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int cnt = 0;
        int answer = 0;

        for (int i = 1; i <= B; i++) {
            for (int j = 1; j <= i; j++) {
                cnt++;

                if (A <= cnt && cnt <= B) {
                    answer += i;
                }
            }
        }

        System.out.println(answer);
    }
}
