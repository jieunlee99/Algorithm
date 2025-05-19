import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		for(int i = 0; i < t; i++) {
			BigInteger n = new BigInteger(bf.readLine());
			System.out.println(n.multiply(n));
		}
	}
}