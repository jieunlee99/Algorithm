import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int number1 = Integer.parseInt(st.nextToken());
        int number2 = Integer.parseInt(st.nextToken()); //(1)

        if (number1 == number2) System.out.println(number1); //(2)
        else System.out.println(number1 > number2 ? number1 : number2); //(3)
    }
}