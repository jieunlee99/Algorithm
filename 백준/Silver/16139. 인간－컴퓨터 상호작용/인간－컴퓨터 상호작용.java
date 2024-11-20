import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String input = br.readLine();
        int len = input.length();

        arr = new int[len][26];
        arr[0][input.charAt(0) - 'a'] = 1;

        // 누적 배열 초기화
        for (int i = 1; i < len; i++) {
            char current = input.charAt(i);

            // 이전 값 복사
            for (int j = 0; j < 26; j++) {
                arr[i][j] = arr[i - 1][j];
            }

            // 현재 문자의 누적 값 증가
            arr[i][current - 'a'] += 1;
        }

        int Q = Integer.parseInt(br.readLine());

        for (int q = 0; q < Q; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char alpha = st.nextToken().charAt(0);
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            // 누적 값을 이용한 결과 계산
            if (left == 0) {
                sb.append(arr[right][alpha - 'a']).append("\n");
            } else {
                sb.append(arr[right][alpha - 'a'] - arr[left - 1][alpha - 'a']).append("\n");
            }
        }

        System.out.print(sb.toString());
    }
}
