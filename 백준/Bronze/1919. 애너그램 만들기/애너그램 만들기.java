import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        String B = br.readLine();

        int[] arrA = new int[26];
        int[] arrB = new int[26];

        for (int i = 0; i < A.length(); i++) {
            arrA[A.charAt(i) - 'a']++;
        }

        for (int i = 0; i < B.length(); i++) {
            arrB[B.charAt(i) - 'a']++;
        }

        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (arrA[i] != arrB[i]) {
                cnt += Math.abs(arrA[i] - arrB[i]);
            }
        }

        System.out.println(cnt);
    }
}
