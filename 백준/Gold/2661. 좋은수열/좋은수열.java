import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static boolean found = false;
	static final char[] digits = { '1', '2', '3' };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dfs(new StringBuilder());

	}

	static void dfs(StringBuilder sequence) {
		if (found)
			return;

		if (sequence.length() == N) {
			System.out.println(sequence);
			found = true;
			return;
		}

		for (char digit : digits) {
			sequence.append(digit);

			if (isGoodSequence(sequence)) {
				dfs(sequence);
			}

			sequence.setLength(sequence.length() - 1);
		}
	}

	static boolean isGoodSequence(StringBuilder sequence) {
		int len = sequence.length();

		for (int i = 1; i <= len / 2; i++) {

			String last = sequence.substring(len - i);
			String beforeLast = sequence.substring(len - i * 2, len - i);

			if (last.equals(beforeLast)) {
				return false;
			}
		}
		
		return true;
	}
}
