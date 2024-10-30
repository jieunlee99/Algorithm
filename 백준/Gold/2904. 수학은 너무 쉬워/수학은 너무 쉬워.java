
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 1000000 + 1;

    static int[] arr = new int[100]; // 입력 저장
    static int[] factor = new int[MAX]; // 빈도수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            countFactor(arr[i]); // 빈도 수 계산
        }

        int score = 1;
        int move = 0;

        for (int i = 2; i < MAX; i++) {
            if (factor[i] >= n) {
                int repeat = factor[i] / n;
                int want = 1;
                
                for (int j = 0; j < repeat; j++) {
                    want *= i;
                }

                for (int j = 0; j < n; j++) {
                    int temp = arr[j];
                    while (temp % want != 0) { // want로 나눠지지 않는다면 추가
                        temp *= i;
                        move++; // 움직임 추가
                    }
                }

                // 최대공약수에 곱해줌
                while (repeat-- > 0) {
                    score *= i;
                }
            }
        }

        System.out.println(score + " " + move);
    }

    public static void countFactor(int n) {
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                n /= i;
                factor[i]++;
            }
        }
        if (n != 1) {                 // n이 1이 아니면 남은 소인수로 취급
            factor[n]++;
        }
    }
}

