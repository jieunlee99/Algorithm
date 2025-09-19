import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 2; i*i <= N; i++) {
			while (N % i == 0) {
				sb.append(i).append("\n");
				N /= i;
			}
		}
        
        if (N > 1) sb.append(N).append("\n"); // 남은 소수 출력

		System.out.println(sb);
	}
}
