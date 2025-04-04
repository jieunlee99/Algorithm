import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<5; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num%10 == N) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
