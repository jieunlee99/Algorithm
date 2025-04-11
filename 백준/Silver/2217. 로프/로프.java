import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Integer[] ropes = new Integer[N];

		for (int i = 0; i < N; i++) {
			ropes[i] = Integer.parseInt(br.readLine());
		}

		// 내림차순 정렬
		Arrays.sort(ropes, Collections.reverseOrder());

		int maxWeight = 0;
		for (int i = 0; i < N; i++) {
			int weight = ropes[i] * (i + 1);
			maxWeight = Math.max(maxWeight, weight);
		}

		System.out.println(maxWeight);
	}

}
