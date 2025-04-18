import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i<3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int front = 0;

            for (int j = 0; j < 4; j++) {
                String str = st.nextToken();
                if (str.equals("0")) {
                    front++;
                }
            }

            switch (front) {
                case 1:
                    sb.append("A").append("\n"); break;
                case 2:
                    sb.append("B").append("\n"); break;
                case 3:
                    sb.append("C").append("\n"); break;
                case 4:
                    sb.append("D").append("\n"); break;
                case 0:
                    sb.append("E").append("\n"); break;
                default: break;
            }
        }
        
        System.out.println(sb);
    }
}