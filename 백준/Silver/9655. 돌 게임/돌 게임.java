import java.io.*;
import java.util.*;

public class Main {
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N%2 != 0) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
		
		br.close();
	}

}
