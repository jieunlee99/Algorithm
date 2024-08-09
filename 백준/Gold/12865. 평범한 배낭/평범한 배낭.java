import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static int[][] dp;
	static Item[] items;

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("src/DAY10/P12865/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 물품의 수
		K = Integer.parseInt(st.nextToken()); // 최대 무게
		
		items = new Item[N+1];
		dp = new int[N+1][K+1];
		
		int W, V;
		for(int n=1; n<=N; n++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken()); // 무게
			V = Integer.parseInt(st.nextToken()); // 가치
			
			items[n] = new Item(W, V);
		}
		
		
		for(int j=1; j<=K; j++) {
			if(items[1].weight>j) {
				dp[1][j] = 0;
			} else {
				dp[1][j] = items[1].value;
			}
		}
		
		int maxValue = Integer.MIN_VALUE;
		
		for(int i=2; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				// dp[i][j] : item i번까지 활용하여 무게 j까지 채웠을 때 가치의 최대값
			
				if(items[i].weight > j) {
					dp[i][j] = dp[i-1][j];
				} else {
					dp[i][j] = Math.max(dp[i-1][j], items[i].value + dp[i-1][j-items[i].weight]);
				}
				
			}
		}
		
		System.out.println(dp[N][K]);
		
	}

}

class Item {
	int weight;
	int value;
	
	public Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Item [weight=" + weight + ", value=" + value + "]";
	}
}