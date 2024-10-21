import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        // 최대 힙 (jewel의 value로 정렬)
        PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparing(Jewel::getValue).reversed());

        Jewel[] jewels;
        long[] bags;

        // System.setIn(new FileInputStream("src/P1202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        jewels = new Jewel[N];
        bags = new long[K];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, value);
        }
        // 보석 무게 오름차순 정렬
        Arrays.sort(jewels, Comparator.comparing(Jewel::getWeight));

        for(int i=0; i<K; i++) {
            bags[i] = Long.parseLong(br.readLine());
        }
        // 가방 무게 오름차순 정렬
        Arrays.sort(bags);


        // 가방 순회
        // 가방에 넣을 수 있는 보석을 우선순위 큐에 넣는다.

        long maxSum = 0;

        int j=0;

        for(int i=0; i<K; i++) {
            while(j<N) {
                if(bags[i]>= jewels[j].weight) {
                    pq.add(jewels[j++]);
                } else {
                    break;
                }
            }

            if(!pq.isEmpty()) {
                maxSum += pq.poll().value;
            }
        }


        System.out.println(maxSum);
    }
}

class Jewel {
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

    @Override
    public String toString() {
        return "Jewel{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}