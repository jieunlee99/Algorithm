
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P2096/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 슬라이딩 윈도우를 위한 배열 사용
        int[] maxDp = new int[3];
        int[] minDp = new int[3];
        int[] tempMax = new int[3];
        int[] tempMin = new int[3];

        // 첫 번째 줄 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<3; i++) {
            int value = Integer.parseInt(st.nextToken());
            maxDp[i] = value;
            minDp[i] = value;
        }

        // 두 번째 줄부터 N번째 줄까지 처리하기
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                int value = Integer.parseInt(st.nextToken());

                // 최대값 계산
                if(j == 0) {
                    tempMax[j] = Math.max(maxDp[0], maxDp[1])+value;
                } else if(j==1) {
                    tempMax[j] = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2])+value;
                } else {
                    tempMax[j] = Math.max(maxDp[1], maxDp[2])+value;
                }

                // 최소값 계산
                if(j == 0) {
                    tempMin[j] = Math.min(minDp[0], minDp[1])+value;
                } else if(j==1) {
                    tempMin[j] = Math.min(Math.min(minDp[0], minDp[1]), minDp[2])+value;
                } else {
                    tempMin[j] = Math.min(minDp[1], minDp[2])+value;
                }
            }

            System.arraycopy(tempMax, 0, maxDp, 0, 3);
            System.arraycopy(tempMin, 0, minDp, 0, 3);
        }

        int maxResult = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
        int minResult = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);

        System.out.println(maxResult+" "+minResult);
    }
}
