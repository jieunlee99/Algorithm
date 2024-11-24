
import java.io.*;
import java.util.*;

public class Main {

	static long A, B, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		long answer = pow(A, B);
		System.out.println(answer);
	}

	static long pow(long num, long exp) {
		if (exp == 1) {
			return num % C;
		}

		long temp = pow(num, exp / 2);

		if (exp % 2 == 1) {
			return (temp * temp % C) * num % C;
		}

		return temp * temp % C;
	}
}
