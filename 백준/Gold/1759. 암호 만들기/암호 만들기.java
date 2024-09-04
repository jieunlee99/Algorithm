
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static char[] input;
    static char[] result;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        input = new char[C]; // 주어진 문자들
        result = new char[L]; // 암호 저장

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++) {
            input[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(input);

        dfs(0, 0);
    }

    public static void dfs(int start, int depth) {

        // 목적지인가?
        if(depth == L) {
            if(isValid()) {
                System.out.println(new String(result));
            }
            return;
        }

        // 순회
        for(int i=start; i<C; i++) {
            // 선택 후 go
            result[depth] = input[i];
            dfs(i+1, depth+1);
        }
    }

    public static boolean isValid() {
        int vowels = 0; // 모음 개수
        int consonants = 0; // 자음 개수

        for(char c:result) {
            if(isVowel(c)) {
                vowels++;
            } else {
                consonants++;
            }
        }

        // 각 암호는 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어야 한다.
        return vowels>=1 &&  consonants>=2;
    }

    // 모음인가?
    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
