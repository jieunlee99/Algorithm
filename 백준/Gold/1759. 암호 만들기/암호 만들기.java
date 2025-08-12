import java.io.*;
import java.util.*;

public class Main {

	static int L, C;

	static char[] alpha;
	static char[] result;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		alpha = new char[C];
		result = new char[L];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(alpha);

		backtracking(0, 0);

		System.out.print(sb);
	}

	static void backtracking(int start, int depth) {

		if (depth == L) {
			if (isValidWord()) {
				sb.append(new String(result)).append("\n");
			}
			return;
		}

		for (int i = start; i < C; i++) {
			result[depth] = alpha[i];
			backtracking(i + 1, depth + 1);
		}
	}

	// 최소 모음 1개, 자음 2개 이상 있어야 함
	static boolean isValidWord() {

		int cntVowel = 0;
		int cntConsonant = 0;

		for (char c : result) {
			if (isVowel(c)) {
				cntVowel++;
			} else {
				cntConsonant++;
			}
		}

		return cntVowel >= 1 && cntConsonant >= 2;

	}

	static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
