import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static Jewel[] jewels;
	static long[] bags;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		jewels = new Jewel[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		// 보석 무게 오름차순 정렬
		Arrays.sort(jewels, Comparator.comparing(Jewel::getWeight));

		bags = new long[K];
		for (int i = 0; i < K; i++) {
			bags[i] = Long.parseLong(br.readLine());
		}

		// 가방 무게 오름차순 정렬
		Arrays.sort(bags);

		PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparing(Jewel::getValue).reversed());
		long maxSum = 0;

		// 가방 순회

		int t = 0;
		for (int i = 0; i < K; i++) {
			while (t < N) {
				if (bags[i] >= jewels[t].weight) { // 가방에 공간이 있다
					pq.offer(jewels[t++]);
				} else {
					break;
				}
			}

			if (!pq.isEmpty()) {
				maxSum += pq.poll().value;
			}
		}

		System.out.println(maxSum);
	}

	static class Jewel {
		int weight, value;

		public Jewel(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		public int getWeight() {
			return weight;
		}

		public int getValue() {
			return value;
		}
	}
}
