import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Point[] checkpoint;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		checkpoint = new Point[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			checkpoint[i] = new Point(x, y);
		}

		int totalDist = 0;
		for (int i = 0; i < N - 1; i++) {
			totalDist += calcDist(checkpoint[i], checkpoint[i + 1]);
		}

		int maxSaved = 0;

		// 생략 가능한 지점은 1 ~ N-2
		for (int i = 1; i < N - 1; i++) {
			int before = calcDist(checkpoint[i - 1], checkpoint[i]) + calcDist(checkpoint[i], checkpoint[i + 1]);
			int after = calcDist(checkpoint[i - 1], checkpoint[i + 1]);
			maxSaved = Math.max(maxSaved, before - after);
		}

		System.out.println(totalDist - maxSaved);
	}

	static int calcDist(Point a, Point b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}

	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
