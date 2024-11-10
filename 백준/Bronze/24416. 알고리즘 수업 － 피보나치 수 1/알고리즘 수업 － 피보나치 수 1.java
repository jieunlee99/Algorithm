import java.util.*;
import java.io.*;

public class Main {

	static int[] fibo;
	static int cnt1=0, cnt2=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		fibo = new int[n+1];
		
		fibonacci1(n);
		fibonacci2(n);
		
		bw.write(cnt1+" "+cnt2+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}

	static int fibonacci1(int n) {

		if (n == 1 || n == 2) {
			cnt1++;
			return 1;
		}
		
		return fibonacci1(n-1)+fibonacci1(n-2);
	}
	
	static int fibonacci2(int n) {
		fibo[1] = 1;
		fibo[2] = 1;
		
		for(int i=3;i<=n; i++) {
			cnt2++;
			fibo[i] = fibo[i-2]+fibo[i-1];
		}
		
		return fibo[n];
	}
}
