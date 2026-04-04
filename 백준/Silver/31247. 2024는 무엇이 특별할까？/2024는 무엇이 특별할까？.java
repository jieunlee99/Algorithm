import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());

            // K가 60 이상이면 2^K가 N의 최댓값(10^18)을 초과하므로 조건을 만족하는 수가 없음
            if (K >= 60) {
                sb.append(0).append("\n");
            } else {
                // 2의 거듭제곱 부분과 나머지 홀수 소수들의 곱으로 분리
                // X = 2^e * m (m은 홀수)
                
                long powerK = 1L << K;          // 2^K
                long powerK1 = 1L << (K + 1);   // 2^(K+1)

                // 2^K의 배수 개수에서 2^(K+1)의 배수 개수를 뺌
                // 우리가 최종적으로 찾아야 하는 것은 1부터 $N$ 이하의 수 중에서 $2^K$의 배수이면서, $2^{K+1}$의 배수는 아닌 수의 개수
                long cnt = (N / powerK) - (N / powerK1);
                sb.append(cnt).append("\n");
            }
        }

        System.out.print(sb);
    }
}