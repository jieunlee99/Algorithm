
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        // System.setIn(new FileInputStream("src/P1072/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long games = Integer.parseInt(st.nextToken());
        long wins = Integer.parseInt(st.nextToken());

        // 현재 승률 계산
        long currentWinRate = wins*100 / games;

        // 이분 탐색 초기화
        long left = 0;
        long right = Integer.MAX_VALUE;
        long answer = -1;

        // 이분 탐색
        while(left <= right) {
            long mid = (left + right)/2;

            // 앞으로의 모든 게임에서 이기기만 함
            long newWins = wins+mid;
            long newGames = games+mid;
            long newWinRate = newWins*100 / newGames;

            if(newWinRate > currentWinRate) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        System.out.println(answer);
    }
}
