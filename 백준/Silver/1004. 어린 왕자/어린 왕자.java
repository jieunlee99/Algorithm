import java.util.*;
import java.io.*;

public class Main {

	static int T;
	static int x1, y1, x2, y2;
	static int n;
	static int cx, cy, r;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(br.readLine());

			int cnt = 0;

			while (n-- > 0) {
				st = new StringTokenizer(br.readLine());
				cx = Integer.parseInt(st.nextToken());
				cy = Integer.parseInt(st.nextToken());
				r = Integer.parseInt(st.nextToken());

				boolean startInside = isInside(x1, y1, cx, cy, r);
				boolean endInside = isInside(x2, y2, cx, cy, r);

				// 한 점만 원 내부에 있는 경우만 카운트 증가
				if (startInside != endInside) {
					cnt++;
				}
			}

			bw.write(cnt + "\n");
		}

		bw.flush();

		br.close();
		br.close();
	}

	static boolean isInside(int px, int py, int cx, int cy, int r) {
		int dx = px - cx;
		int dy = py - cy;
		return dx * dx + dy * dy < r * r;
	}
}
