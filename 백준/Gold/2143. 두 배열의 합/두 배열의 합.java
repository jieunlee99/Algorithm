import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int T, N, M;

	static int[] A;
	static int[] B;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		A = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		B = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		// 부배열의 합 만들기
		ArrayList<Long> listA = new ArrayList<>();
		ArrayList<Long> listB = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			long temp = 0;
			for (int j = i; j < N; j++) {
				temp += A[j];
				listA.add(temp);
			}
		}
		Collections.sort(listA);

		for (int i = 0; i < M; i++) {
			long temp = 0;
			for (int j = i; j < M; j++) {
				temp += B[j];
				listB.add(temp);
			}
		}
		Collections.sort(listB, Comparator.reverseOrder());


		// PA, PB 만들어서 sum과 T 비교
		int pa = 0, pb = 0;
		long cnt = 0;

		while (true) {
			long currentA = listA.get(pa);
			long currentB = listB.get(pb);
			long sum = currentA + currentB;

			if (sum < T) {
				pa++;
			} else if (sum > T) {
				pb++;
			} else { // sum == T
				
				long cntA = 0;
				while(pa < listA.size() && listA.get(pa) == currentA) {
					cntA++;
					pa++;
				}
				
				long cntB = 0;
				while(pb < listB.size() && listB.get(pb) == currentB) {
					cntB++;
					pb++;
				}
				
				cnt+= cntA*cntB;
				


			}
            
            				if(pa == listA.size() || pb == listB.size()) {
					break;
				}
		}

		System.out.println(cnt);
	}

}
