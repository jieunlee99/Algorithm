
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] tree;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        S = 1;
        while (S <= 1000000) {
            S *= 2;
        }

        tree = new long[2 * S];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken()); // 사탕의 맛을 나타내는 정수 (1 ~ 1,000,000)
            if (A == 1) { // 사탕상자에서 사탕을 꺼냄 (query, update)
                // 꺼낼 사탕의 맛의 번호를 출력한다.
                int flavor = query(B);
                sb.append(flavor).append("\n");
                update(flavor, -1);
            } else if (A == 2) { // 사탕을 넣음 (update)
                int C = Integer.parseInt(st.nextToken()); // 사탕의 개수
                update(B, C);
            }
        }

        System.out.print(sb);
    }

    // bottom up 방식
    static void update(int index, int diff) {
        index += S - 1;
        tree[index] += diff;
        while (index > 1) {
            index /= 2;
            tree[index] = tree[2 * index] + tree[2 * index + 1];
        }
    }

    static int query(long k) {
        int node = 1;
        while (node < S) {
            if (tree[2 * node] >= k) {
                node = 2 * node;
            } else {
                k -= tree[2 * node];
                node = 2 * node + 1;
            }
        }
        return node - S + 1;
    }
}

// package P2243;
//
//         import java.io.BufferedReader;
//         import java.io.FileInputStream;
//         import java.io.InputStreamReader;
//         import java.util.StringTokenizer;
//
// public class Main {
//
//     static long[] tree;
//     static int S = 1;
//
//     // 트리의 값을 업데이트하는 함수 (Top-down 방식)
//     static void update(int node, int start, int end, int index, long diff) {
//         if (index < start || index > end) return; // 범위 밖이면 무시
//         tree[node] += diff; // 현재 노드 갱신
//         if (start != end) { // 리프 노드가 아니면 자식 노드로 재귀
//             int mid = (start + end) / 2;
//             update(2 * node, start, mid, index, diff);
//             update(2 * node + 1, mid + 1, end, index, diff);
//         }
//     }
//
//     // k번째 사탕의 맛을 찾아내는 함수 (Top-down 방식)
//     static int query(int node, int start, int end, long k) {
//         if (start == end) {
//             return start;
//         }
//         int mid = (start + end) / 2;
//         if (tree[2 * node] >= k) {
//             return query(2 * node, start, mid, k); // 왼쪽 자식으로 이동
//         } else {
//             return query(2 * node + 1, mid + 1, end, k - tree[2 * node]); // 오른쪽 자식으로 이동
//         }
//     }
//
//     public static void main(String[] args) throws Exception {
//         System.setIn(new FileInputStream("src/P2243/input.txt"));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;
//
//         int N = Integer.parseInt(br.readLine());
//
//         // 트리 크기 설정 (S는 2의 제곱수로, 1,000,000보다 큰 최소값)
//         while (S <= 1000000) {
//             S *= 2;
//         }
//         tree = new long[2 * S];
//
//         // 명령어 처리
//         for (int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             int A = Integer.parseInt(st.nextToken());
//             int B = Integer.parseInt(st.nextToken());
//
//             if (A == 1) {  // 사탕 꺼내기 (k번째 사탕의 맛 번호 출력)
//                 int flavor = query(1, 1, S, B);
//                 System.out.println(flavor);
//                 update(1, 1, S, flavor, -1);  // 사탕을 꺼냈으니 개수를 1 줄임
//             } else if (A == 2) {  // 사탕 넣기
//                 int C = Integer.parseInt(st.nextToken());
//                 update(1, 1, S, B, C);  // B번 맛 사탕을 C개 더함
//             }
//         }
//     }
// }

