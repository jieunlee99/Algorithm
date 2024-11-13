
import java.util.*;
import java.io.*;

public class Main {

	static int N, F;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		F = Integer.parseInt(br.readLine());

		int answer = solution();
		if(answer < 10) {
			sb.append("0");
		}
		sb.append(answer);
		
		System.out.println(sb);
		
	}

	static int solution() {
		
		int answer = N - N % 100;

		while (answer % F != 0) {
			answer++;
		}

		return answer%100;
	}
}
