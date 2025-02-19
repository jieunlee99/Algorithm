
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum1 = 0;
		for(int i=0; i<4; i++) {
			sum1 += Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int sum2 = 0;
		for(int i=0; i<4; i++) {
			sum2 += Integer.parseInt(st.nextToken());
		}
		
		System.out.println(sum1 >= sum2 ? sum1 : sum2);
	}

}
