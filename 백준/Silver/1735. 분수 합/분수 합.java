
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a1 = Integer.parseInt(st.nextToken());
        int b1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int a2 = Integer.parseInt(st.nextToken());
        int b2 = Integer.parseInt(st.nextToken());

        int down = lcm(b1, b2); // 합 분모
        int up = down/b1*a1+down/b2*a2; // 합 분자
        int gcd = gcd(down, up);
        System.out.println(up/gcd+" "+down/gcd);
    }

    static int gcd(int a, int b) {
        int r;
        while(b!=0) {
            r = a%b;
            a = b;
            b = r;
        }
        return a;
    }

    static int lcm(int a, int b) {
        return (a*b)/gcd(a, b);
    }
}
