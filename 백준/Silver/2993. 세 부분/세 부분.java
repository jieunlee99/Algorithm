import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		int len = input.length();

		ArrayList<String> arrayList = new ArrayList<String>();
		for (int a = 1; a < len; a++) {
			for (int b = a + 1; b < len; b++) {
				arrayList.add(makeWord(input, a, b));
			}
		}
		
		Collections.sort(arrayList);
		
		System.out.println(arrayList.get(0));
	}

	static String makeWord(String input, int a, int b) {
		StringBuilder s1 = new StringBuilder(input.substring(0, a)).reverse();
		StringBuilder s2 = new StringBuilder(input.substring(a, b)).reverse();
		StringBuilder s3 = new StringBuilder(input.substring(b)).reverse();

		StringBuilder sb = new StringBuilder();
		sb.append(s1).append(s2).append(s3);
		return sb.toString();
	}
}
