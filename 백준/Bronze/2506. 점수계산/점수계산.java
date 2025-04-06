import java.io.*;
import java.util.*;

public class Main {
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int score = 0;
		boolean isSequential = false;
		int current = 1;
		
		for(int i=1; i<=N; i++) {
			int q = Integer.parseInt(st.nextToken());
			
			if(q == 1) {
				score += current;
				current++;
				isSequential = true;
			} else {
				isSequential = false;
				current = 1;
			}
		}
		
		System.out.println(score);
	}

}
