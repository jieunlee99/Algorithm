import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[] arr = new boolean[3];
        boolean flag = true; // 777인지 확인 하는 flag
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 7) {
                    arr[i] = true;
                    break;
                }
            }
            if (!arr[i]) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println(777);
        } else {
            System.out.println(0);
        }
    }
}