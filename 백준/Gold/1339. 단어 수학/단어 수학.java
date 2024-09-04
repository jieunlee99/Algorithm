
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P1339/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] words = new String[N];
        int[] weight = new int[26]; // 알파벳 가중치 저장(Z~A 순)

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        for (String word : words) {
            int length = word.length();
            for (int i = 0; i < length; i++) {
                char ch = word.charAt(i);
                weight[ch - 'A'] += Math.pow(10, length - 1 - i);
            }
        }

        Arrays.sort(weight);

        int num = 9;
        int result = 0;
        for (int i = 25; i >= 0; i--) {
            if (weight[i] == 0) break;
            result += weight[i] * num;
            num--;
        }

        System.out.println(result);
    }
}
