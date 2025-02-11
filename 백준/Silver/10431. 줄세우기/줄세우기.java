
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int P = Integer.parseInt(br.readLine());

		for (int p = 0; p < P; p++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[20];
			
			for(int i=0; i<20; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int count = 0;
			for(int i=1; i<20; i++) {
				for(int j=0; j<=i; j++) {
					if(arr[i] < arr[j]) {
						count++;
					}
				}
			}
			
			sb.append(T).append(" ").append(count).append("\n");
		}
		
		System.out.println(sb);
	}

}
