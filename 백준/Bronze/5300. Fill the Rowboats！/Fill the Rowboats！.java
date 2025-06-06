import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			sb.append(i).append(" ");
			if(i%6 == 0 || i == N) {
				sb.append("Go!").append(" ");
			}
		}
		
		System.out.println(sb);
	}

}
