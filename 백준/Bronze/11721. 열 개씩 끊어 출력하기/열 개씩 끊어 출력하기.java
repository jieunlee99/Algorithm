import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        for(int i=1; i<=input.length(); i++) {
            char c = input.charAt(i-1);
            System.out.print(c);
            if(i%10 == 0) {
                System.out.println();
            }
        }
    }
}