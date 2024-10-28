import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        BigInteger numA = BigInteger.ONE;
        BigInteger numB= BigInteger.ONE;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            BigInteger num = new BigInteger(st.nextToken());
            numA = numA.multiply(num);
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            BigInteger num = new BigInteger(st.nextToken());
            numB = numB.multiply(num);
        }

        BigInteger gcd = numA.gcd(numB);

        String result = gcd.toString();
        if (result.length() > 9) {
            result = result.substring(result.length() - 9);  // 마지막 9자리 추출
        }
        System.out.println(result);
    }
}
