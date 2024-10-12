
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(findAnswer(N, K));
    }

    static int findAnswer(int n, int k) {
        int[] arr = new int[n+1];

        for(int i=2; i<=n; i++) {
            arr[i] = i;
        }

        for(int i=2; i<=n; i++) {
            // 이미 지워진 값은 건너뛰기
            if(arr[i] == 0) continue;

            for(int j=i; j<=n; j+=i) {
                if(arr[j]!=0) {
                    arr[j] = 0;
                    k--;

                    if(k==0) {
                        return j;
                    }
                }
            }
        }

        return -1;
    }
}
