import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BigInteger N = new BigInteger(br.readLine()); // 전체 개수
        BigInteger M = new BigInteger(br.readLine()); // 차이

        // x = (N - M) / 2
        BigInteger x = (N.subtract(M)).divide(BigInteger.TWO);

        System.out.println(x.add(M)); // 큰 수 (x + M)
        System.out.println(x);        // 작은 수 (x)
    }
}
