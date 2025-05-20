import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i ++){
            String s = br.readLine();

            if(s.length() >= 10 || s.length() < 6)
                sb.append("no");
            else
                sb.append("yes");

            sb.append("\n");
        }
        System.out.print(sb);
    }
}
