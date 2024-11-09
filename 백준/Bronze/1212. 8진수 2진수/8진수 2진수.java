import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			String temp = Integer.toBinaryString(input.charAt(i) - 48);
			if (temp.length() < 3 && i != 0) {
				for (int j = 0; j < 3 - temp.length(); j++) {
					sb.append(0);
				}
			}
			sb.append(temp);
		}

		System.out.println(sb);
	}

}
