
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}

		// AB[pa] + CD[pb] = 0
		
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

		// 정렬
		Arrays.sort(AB);
		Arrays.sort(CD);

		// 투 포인터
		int pa = 0, pb = CD.length - 1;
		long count = 0;

		while (pa < AB.length && pb >= 0) {
			
			long sum = AB[pa] + CD[pb];

			if (sum == 0) {
				
				long cntAB = 0, cntCD = 0;
				long currentAB = AB[pa];
				long currentCD = CD[pb];

				while (pa < AB.length && AB[pa] == currentAB) {
					cntAB++;
					pa++;
				}

				while (pb >= 0 && CD[pb] == currentCD) {
					cntCD++;
					pb--;
				}

				count += cntAB * cntCD;
				
			} else if (sum < 0) {
				pa++;
			} else {
				pb--;
			}
		}

		System.out.println(count);

	}

}
