import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.parseLong(st.nextToken());
		long m = Long.parseLong(st.nextToken());

		// n!/(n-m)!m!에 대한 승수를 5와 2에 대해 구한다.
		long cnt5 = cntPowerFive(n) - cntPowerFive(n - m) - cntPowerFive(m);
		long cnt2 = cntPowerTwo(n) - cntPowerTwo(n - m) - cntPowerTwo(m);
		
		System.out.println(Math.min(cnt5, cnt2));
	}

	static long cntPowerFive(long num) {
		int cnt = 0;
		while (num >= 5) {
			cnt += (num / 5);
			num /= 5;
		}
		return cnt;
	}

	static long cntPowerTwo(long num) {
		int cnt = 0;
		while (num >= 2) {
			cnt += (num / 2);
			num /= 2;
		}
		return cnt;
	}

}
