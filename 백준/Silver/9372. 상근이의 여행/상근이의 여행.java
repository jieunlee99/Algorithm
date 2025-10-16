import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int T, N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 국가 수
			M = Integer.parseInt(st.nextToken()); // 비행기 종류

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
			}
			
			sb.append(N - 1).append("\n");
		}

		System.out.println(sb);
	}

}
