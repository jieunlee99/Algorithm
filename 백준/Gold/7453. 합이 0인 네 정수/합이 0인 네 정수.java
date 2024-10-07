import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P7453/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        // A와 B, C와 D의 부분합을 저장할 배열
        long[] AB = new long[N * N];
        long[] CD = new long[N * N];

        // A와 B의 조합 합
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx++] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        // AB는 오름차순, CD는 내림차순으로 투 포인터 사용
        int pa = 0, pb = CD.length - 1;
        long count = 0;

        while (pa < AB.length && pb >= 0) {
            long sum = AB[pa] + CD[pb];

            if (sum == 0) {
                long cntA = 0, cntB = 0;
                long currentA = AB[pa];
                long currentB = CD[pb];

                while (pa < AB.length && AB[pa] == currentA) {
                    cntA++;
                    pa++;
                }

                while (pb >= 0 && CD[pb] == currentB) {
                    cntB++;
                    pb--;
                }

                count += cntA * cntB;
            } else if (sum < 0) {
                pa++;
            } else {
                pb--;
            }
        }

        System.out.println(count);
    }
}