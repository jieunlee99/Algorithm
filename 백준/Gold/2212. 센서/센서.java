import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 센서의 개수
        K = Integer.parseInt(br.readLine()); // 집중국의 개수

        int[] censor = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            censor[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(censor);

        int[] diff = new int[N-1];
        for (int i = 0; i < N-1; i++) {
            diff[i] = Math.abs(censor[i] - censor[i + 1]);
        }

        Arrays.sort(diff);

        int sum = 0;
        for(int i=0; i<N-K; i++) {
            sum += diff[i];
        }

        System.out.println(sum);
    }
}
