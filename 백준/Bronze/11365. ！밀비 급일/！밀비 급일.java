import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {

			String input = br.readLine();
			if (input.equals("END")) {
				 break;
			}
			
			char[] arr = input.toCharArray();
			
			for(int i=arr.length-1; i>=0; i--) {
				sb.append(arr[i]);
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

}
