import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] B = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            diff[i] = B[i] - A[i];
        }

        Arrays.sort(diff); // 필요한 금액 순으로 오름차순 정렬

        // K개를 낙찰받으려면, 정렬된 배열의 K번째 값(인덱스 K-1)만큼만 X를 올리면 됨
        int answer = diff[K - 1];

        // 단, 필요한 금액이 음수면 이미 낙찰된 것이므로 0과 비교해서 큰 값 출력
        System.out.println(Math.max(0, answer));
    }
}
