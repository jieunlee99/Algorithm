
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] A, B;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N];
        B = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 1_000_000_000;

        int answer = Integer.MAX_VALUE;

        while(left<=right) {
            int mid = (left+right)/2;

            int cnt = count(mid);

            if(cnt >= K) {
                answer = Math.min(answer, mid);
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        if(answer == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }

    public static int count(int x) {
        int cnt = 0;
        for(int i=0; i<N; i++) {
            if(A[i] + x >= B[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}
