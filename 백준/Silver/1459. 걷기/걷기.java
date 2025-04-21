
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		long x = Long.parseLong(st.nextToken());
		long y = Long.parseLong(st.nextToken());
		long w = Long.parseLong(st.nextToken());
		long s = Long.parseLong(st.nextToken());

		long minXY = Math.min(x, y);
		long maxXY = Math.max(x, y);
		long diff = maxXY - minXY;

		// 1. 직선만
		long case1 = (x + y) * w;

		// 2. 대각선+직선
		long case2 = 0;
		if (s < w * 2) {
			case2 = minXY * s + diff * w;
		} else {
			case2 = (x + y) * w;
		}

		// 3. 대각선만
		long case3 = 0;
		if ((diff % 2) == 0) {
			case3 = minXY * s + diff * s;
		} else {
			case3 = minXY * s + (diff - 1) * s + w;
		}

		long result = Math.min(case1, Math.min(case2, case3));

		System.out.println(result);
	}

}
