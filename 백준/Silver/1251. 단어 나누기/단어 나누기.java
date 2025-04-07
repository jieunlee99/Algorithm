import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String word = br.readLine();

		int len = word.length();

		ArrayList<String> mixed = new ArrayList<>();
		for (int i = 1; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				mixed.add(makeWord(word, i, j));
			}
		}

		Collections.sort(mixed);

		System.out.println(mixed.get(0));
	}

	static String makeWord(String input, int i, int j) {
		StringBuilder s1 = new StringBuilder(input.substring(0, i));
		StringBuilder s2 = new StringBuilder(input.substring(i, j));
		StringBuilder s3 = new StringBuilder(input.substring(j));

		StringBuilder sb = new StringBuilder();
		sb.append(s1.reverse()).append(s2.reverse()).append(s3.reverse());

		return sb.toString();
	}
}
