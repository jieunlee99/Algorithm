import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static ArrayList<Integer> heights;
	static int maxHeight, minHeight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		heights = new ArrayList<>();

		maxHeight = Integer.MIN_VALUE;
		minHeight = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			maxHeight = Math.max(maxHeight, height);
			minHeight = Math.min(minHeight, height);
			heights.add(height);
		}

		int answer = Integer.MAX_VALUE;
		for (int i = minHeight; i < maxHeight - 17; i++) {
			answer = Math.min(answer, calcCost(i, i + 17));
		}

		if (answer == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}

	static int calcCost(int min, int max) {
		int cost = 0;
		for (int i = 0; i < N; i++) {
			int temp = 0;
			if (heights.get(i) > max) {
				temp = (int) Math.pow(heights.get(i) - max, 2);
			} else if (heights.get(i) < min) {
				temp = (int) Math.pow(min - heights.get(i), 2);
			}
			cost += temp;
		}
		return cost;
	}
}