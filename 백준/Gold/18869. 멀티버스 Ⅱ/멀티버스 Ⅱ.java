import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] planet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 우주의 개수
		N = Integer.parseInt(st.nextToken()); // 행성의 개수

		planet = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				planet[i][j] = Integer.parseInt(st.nextToken());
			}

			int[] sortPlanet = Arrays.stream(planet[i]).sorted().distinct().toArray();

			for (int j = 0; j < N; j++) {
				int index = Arrays.binarySearch(sortPlanet, planet[i][j]);
				planet[i][j] = index;
			}
		}

		int answer = 0;
		
		for (int i = 0; i < M - 1; i++) {
			for (int j = i + 1; j < M; j++) {
				if (Arrays.equals(planet[i], planet[j])) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
}
