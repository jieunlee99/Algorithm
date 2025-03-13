
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		int lenA = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == 'a') {
				lenA++;
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < input.length(); i++) {
			int cntB = 0;
			for (int j = i; j < lenA + i; j++) {
				if (input.charAt(j % input.length()) == 'b') {
					cntB++;
				}
			}
			answer = Math.min(answer, cntB);
		}

		System.out.println(answer);
	}

}