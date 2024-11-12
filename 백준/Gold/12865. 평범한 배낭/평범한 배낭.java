import java.util.*;
import java.io.*;

public class Main {

	static int N, K;
	static Item[] items;
	static int[] dp; // dp[j]: 무게 j까지 채웠을 때의 최대 가치

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 물건 개수
		K = Integer.parseInt(st.nextToken()); // 최대 무게

		items = new Item[N + 1];
		dp = new int[K + 1];

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			items[n] = new Item(w, v);
		}

		for (int i = 1; i <= N; i++) {
			for (int j = K; j >= items[i].weight; j--) {
				dp[j] = Math.max(dp[j], items[i].value + dp[j - items[i].weight]);
			}
		}

		bw.write(dp[K] + "\n");
		bw.flush();

		bw.close();
		br.close();
	}
}

class Item {
	int weight, value;

	public Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
}
