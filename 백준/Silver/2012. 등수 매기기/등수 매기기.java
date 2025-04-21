import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] expect = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			expect[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(expect);

		long sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += Math.abs(expect[i] - i);
		}
		
		System.out.println(sum);

	}

}
