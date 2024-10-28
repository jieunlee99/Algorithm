import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        int[] cnt = new int[1001];
        cnt[1] = 3;

        int tmp; // 서로소 쌍의 개수를 저장할 변수
        for(int i=2; i<1001; i++) {
            tmp = 0;
            for(int j=1; j<i; j++) {
                if(gcd(i, j) == 1) {
                    tmp++;
                }
            }
            cnt[i] = cnt[i-1]+tmp*2;
        }

        while (C-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(cnt[n]).append("\n");
        }

        System.out.print(sb);
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
