import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int max = Integer.MIN_VALUE;
		
		int current = 0;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			int out = Integer.parseInt(st.nextToken());
			current -= out;
			
			int in = Integer.parseInt(st.nextToken());
			current += in;
			max = Math.max(current, max);
		}

		System.out.println(max);
	}

}
