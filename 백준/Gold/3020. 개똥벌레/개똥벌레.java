
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, H;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P3020/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 장애물의 개수
        H = Integer.parseInt(st.nextToken()); // 장애물의 최대 높이

        // 종유석과 석순
        int[] top = new int[H + 1];
        int[] bottom = new int[H + 1];

        // 장애물의 시작과 끝만 기록
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                bottom[height]++;
            } else {
                top[height]++;
            }
        }

        // 누적합 계산
        for (int i = H - 1; i >= 1; i--) {
            bottom[i] += bottom[i + 1];
            top[i] += top[i + 1];
        }

        int minError = Integer.MAX_VALUE;
        int cnt = 0;

        // 높이마다 장애물 개수 계산 후 최소값 갱신
        for (int i = 1; i <= H; i++) {
            int totalErrors = bottom[i] + top[H - i + 1];
            if (totalErrors < minError) {
                minError = totalErrors;
                cnt = 1;
            } else if (totalErrors == minError) {
                cnt++;
            }
        }

        System.out.println(minError + " " + cnt);
    }
}
