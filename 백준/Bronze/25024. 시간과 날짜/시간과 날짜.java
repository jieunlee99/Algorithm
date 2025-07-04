import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        for (int i = 0; i < T; i++) {
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);

            canTime(x, y);
            canMonth(x, y);
        }
    }

    //두 정수 x, y를 x시 y분으로 읽을 수 있는가?
    public static void canTime(int x, int y) {
        if (x <= 23 && y <= 59)
            System.out.print("Yes ");
        else
            System.out.print("No ");
    }

    //두 정수 x, y를 x월 y일로 읽을 수 있는가?
    public static void canMonth(int x, int y) {
        int day[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if ((x <= 12 && x > 0) && (y <= day[x - 1] && y > 0))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}