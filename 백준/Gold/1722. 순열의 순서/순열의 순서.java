import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int type;
    static long K;
    static long[] factorial;
    static int[] perm;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        factorial = new long[N + 1];
        factorial[0] = 1;
        for (int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        type = Integer.parseInt(st.nextToken());
        if(type == 1) {
            K = Long.parseLong(st.nextToken());
            findKthPermutation(K);
        } else if(type == 2) {
            perm = new int[N];
            for(int i = 0; i < N; i++) {
                perm[i] = Integer.parseInt(st.nextToken());
            }
            findPermutationOrder();
        }
    }

    private static void findKthPermutation(long K) {
        List<Integer> numbers = new ArrayList<>();
        for(int i=1; i<=N; i++) {
            numbers.add(i);
        }

        K--;
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=N; i++) {
            int idx = (int) (K / factorial[N-i]);
            sb.append(numbers.get(idx)).append(" ");
            numbers.remove(idx);
            K %= factorial[N-i];
        }

        System.out.println(sb.toString().trim());
    }

    private static void findPermutationOrder() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            numbers.add(i);
        }

        long order = 1;
        for (int i = 0; i < N; i++) {
            int idx = numbers.indexOf(perm[i]);
            order += idx * factorial[N - i - 1];
            numbers.remove(idx);
        }

        System.out.println(order);
    }
}
