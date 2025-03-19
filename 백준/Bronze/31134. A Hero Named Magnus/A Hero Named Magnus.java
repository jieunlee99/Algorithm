import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			BigInteger x = new BigInteger(br.readLine());
			sb.append(x.multiply(BigInteger.TWO).subtract(BigInteger.ONE)).append("\n");
		}

		System.out.print(sb);
	}
}
