import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] alpha = new int[26];

        String input = br.readLine().toLowerCase();

        for(int i=0; i<input.length(); i++) {
            alpha[input.charAt(i)-'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<alpha.length; i++) {
            sb.append(alpha[i]).append(" ");
        }

        System.out.println(sb);
    }
}
