import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int a = 0;  // 집합을 표현하는 비트마스크
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();  // 명령어 추출
            int num = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;  // 숫자가 있는 경우만 추출

            switch (op) {
                case "add":
                    a |= (1 << num);
                    break;
                case "check":
                    bw.write(((a & (1 << num)) != 0 ? "1" : "0") + "\n");
                    break;
                case "remove":
                    a &= ~(1 << num);
                    break;
                case "toggle":
                    a ^= (1 << num);
                    break;
                case "all":
                    a = 0x1FFFFF;  // 20개의 비트 모두 1로 설정
                    break;
                case "empty":
                    a = 0;
                    break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
