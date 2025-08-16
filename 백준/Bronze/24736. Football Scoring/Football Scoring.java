import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int scoreA = 0;
        int scoreB = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        scoreA += Integer.parseInt(st.nextToken()) * 6;
        scoreA += Integer.parseInt(st.nextToken()) * 3;
        scoreA += Integer.parseInt(st.nextToken()) * 2;
        scoreA += Integer.parseInt(st.nextToken()) * 1;
        scoreA += Integer.parseInt(st.nextToken()) * 2;

        st = new StringTokenizer(br.readLine());
        scoreB += Integer.parseInt(st.nextToken()) * 6;
        scoreB += Integer.parseInt(st.nextToken()) * 3;
        scoreB += Integer.parseInt(st.nextToken()) * 2;
        scoreB += Integer.parseInt(st.nextToken()) * 1;
        scoreB += Integer.parseInt(st.nextToken()) * 2;

        bw.append(scoreA + " " + scoreB);
        bw.flush();
        bw.close();
        br.close();
    }
}