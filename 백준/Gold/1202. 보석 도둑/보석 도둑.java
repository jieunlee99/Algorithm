import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparing(Jewel::getValue).reversed()); // 최대 힙
		
		Jewel[] jewels;
		long[] bags;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		jewels = new Jewel[N];
		bags = new long[K];
		
		// 보석 무게 오름차순 정렬
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			jewels[i] = new Jewel(weight, value);
		}
		Arrays.sort(jewels, Comparator.comparing(Jewel::getWeight));
//		System.out.println(Arrays.toString(jewels));
		
		// 가방 오름차순 정렬
		for(int i=0; i<K; i++) {
			bags[i] = Long.parseLong(br.readLine());
		}
		Arrays.sort(bags);
//		System.out.println(Arrays.toString(bags));
		
		long maxSum = 0;
		
		// 가방 순회
		// 가방에 넣을 수 있는 보석을 pq에 넣음
		int j=0;
		for(int i=0; i<K; i++) {
			while(j<N) {
				if(bags[i] >= jewels[j].weight) {
					pq.add(jewels[j++]);
				} else {
					break;
				}	
			}
			
			// 이때 pq의 top의 의미는 가방에 넣을 수 있는 가장 비싼 보석
			if(!pq.isEmpty()) {
				maxSum += pq.poll().value;
			}
			
		}
		
		System.out.println(maxSum);
	}
}

class Jewel {
	int weight;
	int value;

	public Jewel(int weight, int value) {
		super();
		this.weight = weight;
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Jewel [weight=" + weight + ", value=" + value + "]";
	}

}