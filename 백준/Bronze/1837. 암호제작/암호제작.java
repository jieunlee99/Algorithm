
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String P = st.nextToken();
        int K = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[K];
        for(int i=2; i<K; i++) {
            isPrime[i] = true;
        }

        for(int i=2; i*i<K; i++) {
            if(isPrime[i]) {
                for(int j=i*i; j<K; j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        for(int i=2; i<K; i++) {
            if(isPrime[i]) {
                if(mod(P, i) == 0) {
                    System.out.println("BAD "+i);
                    return;
                }
            }
        }

        System.out.println("GOOD");
    }

    static int mod(String num, int divisor) {
        int result = 0;
        for (int i = 0; i < num.length(); i++) {
            result = (result * 10 + (num.charAt(i) - '0')) % divisor;
        }
        return result;
    }
}


