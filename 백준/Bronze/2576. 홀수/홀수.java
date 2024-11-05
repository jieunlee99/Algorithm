
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int sumOdd = 0;
		int minOdd = Integer.MAX_VALUE;
		for(int i=0; i<7; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num%2 == 1) {
				sumOdd += num;
				minOdd = Math.min(num, minOdd);
			}
		}
		
		if(sumOdd == 0 && minOdd == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(sumOdd+"\n"+minOdd);
		}
	}

}
