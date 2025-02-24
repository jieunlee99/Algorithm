import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static long M;
	static int[] budgetPlan;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 지방의 수
		
		budgetPlan = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			budgetPlan[i] = Integer.parseInt(st.nextToken());
		}

		M = Long.parseLong(br.readLine());
		
		// high를 최대 요청 예산 값으로 설정해야 함
		long low = 0;
		long high = Arrays.stream(budgetPlan).max().getAsInt();
		long result = 0;
		
		while (low <= high) {
			long mid = (low + high) / 2;
			
			if (canThisLimit(mid)) {
				result = mid;  // 가능한 경우 결과를 저장
				low = mid + 1; // 상한선을 높여본다.
			} else {
				high = mid - 1; // 예산 초과 시 상한선을 낮춘다.
			}
		}
		
		System.out.println(result);
	}
	
	static boolean canThisLimit(long limit) {
		long sum = 0L;
		
		for (int budget : budgetPlan) {
			sum += Math.min(budget, limit);
		}
		return sum <= M;
	}
}
