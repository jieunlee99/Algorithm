
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L;
    static int[] A;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P11003/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            // 1. 덱의 앞에서부터 슬라이딩 윈도우 밖에 있는 인덱스는 제거시킨다.
            if (!deque.isEmpty() && deque.peekFirst() < i - L + 1) {
                deque.pollFirst();
            }

            // 2. 덱의 뒤에서부터 현재 값보다 큰 값의 인덱스 제거 (최소값 유지)
            while (!deque.isEmpty() && A[deque.peekLast()] > A[i]) {
                deque.pollLast();
            }

            // 3. 현재 인덱스 추가
            deque.addLast(i);

            // 4. 현재 슬라이딩 윈도우에서의 최소값을 결과에 추가
            sb.append(A[deque.peekFirst()]).append(" ");
        }

        System.out.println(sb);
    }
}
