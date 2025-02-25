import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		int pointer = 0;
		int base = 0;
		
		
		while(base++ <= 30000) {
			String tmp = String.valueOf(base);
			for(int i=0;  i<tmp.length(); i++) {
				if(tmp.charAt(i) == input.charAt(pointer)) {
					pointer++;
				}
				if(pointer == input.length()) {
					System.out.println(base);
					return;
				}
			}
		}
	}

}
