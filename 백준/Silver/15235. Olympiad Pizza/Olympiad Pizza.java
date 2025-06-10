
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Queue<int[]> queue = new ArrayDeque<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int pizza = Integer.parseInt(st.nextToken());
			queue.offer(new int[] { i, pizza });
		}

		int time = 0;
		int[] result = new int[N];

		while (!queue.isEmpty()) {
			time++;

			int[] current = queue.poll();
			int pizza = current[1] - 1;

			if (pizza == 0) {
				result[current[0]] = time;
			} else {
				queue.offer(new int[] { current[0], pizza });
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(result[i]).append(" ");
		}
		
		System.out.println(sb);
	}

}
