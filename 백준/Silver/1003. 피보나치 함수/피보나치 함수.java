import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[][] fibo = new int[41][2];

		fibo[0][0] = 1;
		fibo[0][1] = 0;
		fibo[1][0] = 0;
		fibo[1][1] = 1;
		
		while(N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			for(int i=2; i<=num; i++) {
				fibo[i][0] = fibo[i-1][0]+fibo[i-2][0];
				fibo[i][1] = fibo[i-1][1]+fibo[i-2][1];
			}
			
			bw.write(fibo[num][0]+" "+fibo[num][1]+"\n");
		}

		bw.flush();

		bw.close();
		br.close();
	}
}
