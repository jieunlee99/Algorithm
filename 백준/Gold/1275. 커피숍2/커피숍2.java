
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static int S;
    static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 수의 개수
        Q = Integer.parseInt(st.nextToken()); // 턴의 개수

        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[2 * S];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            update(i, num);  // 배열의 값을 세그먼트 트리에 업데이트
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (x > y) { // x와 y의 순서가 잘못된 경우, 스왑
                int temp = x;
                x = y;
                y = temp;
            }

            // x ~ y 구간의 합 출력
            long sum = query(x, y);
            System.out.println(sum);

            // a번째 수를 b로 업데이트
            update(a, b);
        }
    }

    // 세그먼트 트리의 값 업데이트
    static void update(int index, long newValue) {
        index += S - 1;
        tree[index] = newValue;

        // 부모 노드로 올라가며 값을 갱신
        while (index > 1) {
            index /= 2;
            tree[index] = tree[2 * index] + tree[2 * index + 1];
        }
    }

    // x ~ y 구간의 합을 구하는 함수
    static long query(int left, int right) {
        left += S - 1;
        right += S - 1;
        long sum = 0;

        while (left <= right) {
            // left가 오른쪽 자식일 때, 구간에 포함되므로 값을 더하고 다음 구간으로 이동
            if (left % 2 == 1) {
                sum += tree[left];
                left++;
            }

            // right가 왼쪽 자식일 때, 구간에 포함되므로 값을 더하고 이전 구간으로 이동
            if (right % 2 == 0) {
                sum += tree[right];
                right--;
            }

            // 부모 노드로 이동
            left /= 2;
            right /= 2;
        }

        return sum;
    }
}
