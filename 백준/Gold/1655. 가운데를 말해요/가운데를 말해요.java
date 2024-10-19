import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // 내림차순 정렬
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2); // 오름차순 정렬

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            // 각 힙의 개수를 비교하여 추가하기 때문에, 그 후 값을 비교하여 크기에 문제가 있을 시 교환시켜줌
            
            if (maxHeap.size() == minHeap.size()) maxHeap.add(num);
            else minHeap.add(num);

            if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
                if (maxHeap.peek() > minHeap.peek()) {
                    int tmp = maxHeap.poll();
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(tmp);
                }
            }

            sb.append(maxHeap.peek()).append("\n");
        }

        System.out.print(sb);
    }
}
