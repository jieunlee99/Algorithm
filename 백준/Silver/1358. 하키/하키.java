import java.io.*;
import java.util.*;

public class Main {

	static int W, H, X, Y, P;
	static int radius;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		int answer = 0;
		
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (inSquare(a, b) || inCircle(a, b)) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}

	static boolean inSquare(int x, int y) {
		return X <= x && x <= X + W && Y <= y && y <= Y + H;
	}

	static boolean inCircle(int x, int y) {
		radius = H / 2;

		if (x < X) { // 왼쪽 반원
			int centerX = X;
			int centerY = Y + radius;
			double dist = calcDist(x, y, centerX, centerY);

			if (dist <= radius) {
				return true;
			}

		} else if (x > X + W) { // 오른쪽 반원
			int centerX = X + W;
			int centerY = Y + radius;
			double dist = calcDist(x, y, centerX, centerY);

			if (dist <= radius) {
				return true;
			}
		}

		return false;
	}

	static double calcDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}
