import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			int min = 100;
			int sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 7; j++) {
				int N = Integer.parseInt(st.nextToken());
				if(N % 2 == 0) {
					sum += N;
					min = Math.min(min, N);
				}
			}
			System.out.println(sum + " " + min);
		}
	}

}