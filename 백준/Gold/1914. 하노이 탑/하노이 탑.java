
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		sb.append(BigInteger.TWO.pow(n).subtract(BigInteger.ONE)).append("\n");

		if (n <= 20) {
			hanoi(n, 1, 2, 3);
		}

		System.out.print(sb);
	}

	static void hanoi(int n, int from, int tmp, int to) {
		if (n == 1) {
			sb.append(from + " " + to).append("\n");
			return;
		}

		hanoi(n - 1, from, to, tmp);
		sb.append(from + " " + to).append("\n");

		hanoi(n - 1, tmp, from, to);
	}

}
