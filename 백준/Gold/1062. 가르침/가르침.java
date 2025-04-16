
import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static boolean[] isVisited = new boolean[26];
	static String[] words;
	static int max = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}

		if (K < 5) {
			System.out.println(0);
			return;
		}

		// 기본 알파벳 체크 (a, n, t, i, c)
		isVisited['a' - 'a'] = true;
		isVisited['n' - 'a'] = true;
		isVisited['t' - 'a'] = true;
		isVisited['i' - 'a'] = true;
		isVisited['c' - 'a'] = true;

		dfs(0, 5); // 시작 인덱스, 현재까지 선택한 알파벳 수

		System.out.println(max);
	}

	static void dfs(int index, int count) {
		if (count == K) {
			max = Math.max(max, countReadableWords());
			return;
		}

		for (int i = index; i < 26; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				dfs(i + 1, count + 1);
				isVisited[i] = false;
			}
		}
	}

	static int countReadableWords() {
		int readable = 0;
		for (String word : words) {
			boolean canRead = true;
			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);
				if (!isVisited[ch - 'a']) {
					canRead = false;
					break;
				}
			}
			if (canRead) {
				readable++;
			}
		}
		return readable;
	}
}
