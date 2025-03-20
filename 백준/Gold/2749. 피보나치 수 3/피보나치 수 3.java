import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());
		long idx = n % 1500000; // 피사노 주기

		long[] fibo = new long[(int) idx + 1];

		fibo[0] = 0;
		fibo[1] = 1;

		for (int i = 2; i <= idx; i++) {
			fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1000000;
		}

		System.out.println(fibo[(int) idx]);
	}
}
