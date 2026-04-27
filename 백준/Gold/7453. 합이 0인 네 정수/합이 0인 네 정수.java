import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] A, B, C, D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		B = new int[N];
		C = new int[N];
		D = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}

		long[] AB = new long[N * N];
		long[] CD = new long[N * N];

		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[idx] = A[i] + B[j];
				CD[idx] = C[i] + D[j];
				idx++;
			}
		}

		Arrays.sort(AB);
		Arrays.sort(CD);

		int p1 = 0, p2 = N * N - 1;
		long count = 0;

		while (p1 < N * N && p2 >= 0) {
			long sum = AB[p1] + CD[p2];

			if (sum == 0) {
				long cntAB = 0, cntCD = 0;
				long currentAB = AB[p1];
				long currentCD = CD[p2];

				while (p1 < AB.length && AB[p1] == currentAB) {
					cntAB++;
					p1++;
				}

				while (p2 >= 0 && CD[p2] == currentCD) {
					cntCD++;
					p2--;
				}

				count += cntAB * cntCD;
				
			} else if (sum < 0) {
				p1++;
			} else {
				p2--;
			}
		}

		System.out.println(count);
	}

}
