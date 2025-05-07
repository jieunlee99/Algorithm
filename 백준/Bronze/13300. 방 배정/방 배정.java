
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int K = Integer.parseInt(st.nextToken()); // 한 방에 배정할 수 있는 최대 인원

        int[][] rooms = new int[3][7];

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()); // 성별 (0여, 1남)
            int Y = Integer.parseInt(st.nextToken()); // 학년
            rooms[S][Y]++;
        }

        int needRoom = 0;
        for (int i = 0; i <= 1; i++) {
            for (int j = 1; j <= 6; j++) {
                needRoom += (int) (Math.ceil((double) rooms[i][j] / K));
            }
        }

        System.out.println(needRoom);
    }
}
