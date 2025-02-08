import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for (int t = 0; t < 3; t++) {
            int N = Integer.parseInt(br.readLine());
            
            BigInteger sum = BigInteger.ZERO;
            
            for (int i = 0; i < N; i++) {
                sum = sum.add(new BigInteger(br.readLine()));
            }
            
            if (sum.equals(BigInteger.ZERO)) {
                sb.append("0\n");
            } else if (sum.compareTo(BigInteger.ZERO) > 0) {
                sb.append("+\n");
            } else {
                sb.append("-\n");
            }
        }
        
        System.out.print(sb);
    }
}