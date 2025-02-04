import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        for(int i=0; i<5; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num < 0 && num > 10) {

            }
            sum += num;
        }

        System.out.println(sum);
    }
}
