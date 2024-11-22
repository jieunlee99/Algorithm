import java.io.*;
import java.util.*;

public class Main {

	static int N;

	static long[] distance;
	static long[] cost;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		distance = new long[N - 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			distance[i] = Long.parseLong(st.nextToken());
		}

		cost = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Long.parseLong(st.nextToken());
		}

		long sum = 0L;

		long minCost = cost[0];

		sum += cost[0] * distance[0];
		for (int i = 1; i < N - 1; i++) {
			if (minCost > cost[i]) {
				minCost = cost[i];
			}
			sum += minCost * distance[i];
		}

		System.out.println(sum);
	}

}
