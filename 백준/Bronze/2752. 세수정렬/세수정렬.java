
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[3];
		for(int i=0; i<3; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<3; i++) {
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb);
	}

}
