import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();
		
		for(int i=0; i<input.length(); i++) {
			char cur = input.charAt(i);
			if(65<= cur && cur <= 90) {
				cur += 32;
			} else if(97<=cur && cur <= 122) {
				cur -= 32;
			}
			sb.append(cur);
		}
		
		System.out.println(sb);
	}

}
