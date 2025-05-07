import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Y -> 30초마다 10원
        // M -> 60초마다 15원

        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        int sumY = 0;
        for (int i = 0; i < N; i++) {
            if (time[i] % 30 == 0) {
                sumY += 10;
            }
            sumY += (int) (Math.ceil((double) time[i] / 30) * 10);
        }

        int sumM = 0;
        for (int i = 0; i < N; i++) {
            if (time[i] % 60 == 0) {
                sumM += 15;
            }
            sumM += (int) (Math.ceil((double) time[i] / 60) * 15);
        }

        StringBuilder sb = new StringBuilder();
        if (sumY < sumM) {
            sb.append("Y ").append(sumY);
        } else if (sumY > sumM) {
            sb.append("M ").append(sumM);
        } else {
            sb.append("Y M ").append(sumY);
        }

        System.out.println(sb);
    }
}
