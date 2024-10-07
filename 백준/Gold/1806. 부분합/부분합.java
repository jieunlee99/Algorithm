
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S; // 수열의 길이, 합
    static int[] nums; // 수열
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P1806/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int low = 0, high = 0;

        while(high < N) {
            sum += nums[high++];

            while(sum >= S) {
                min = Math.min(min, high-low);
                sum -= nums[low++];
            }
        }

        if(min == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }
    }
}
