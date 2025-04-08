import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] a = new int[N];
		int[] b = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(a);
		Arrays.sort(b);

		int s = 0;
		for (int i = 0; i < N; i++) {
			s += a[i] * b[N - i - 1];
		}

		System.out.println(s);
	}
}
