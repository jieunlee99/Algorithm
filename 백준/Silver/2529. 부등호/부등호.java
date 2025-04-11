import java.io.*;
import java.util.*;

public class Main {

	static int k;
	static char[] signs;
	static boolean[] used = new boolean[10];
	static List<String> result = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		k = Integer.parseInt(br.readLine());

		signs = new char[k];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			signs[i] = st.nextToken().charAt(0);
		}

		for (int i = 0; i < 10; i++) {
			used[i] = true;
			dfs(0, i + "");
			used[i] = false;
		}

		Collections.sort(result);

		// 최대, 최소 출력
		System.out.println(result.get(result.size() - 1));
		System.out.println(result.get(0));
	}

	static void dfs(int depth, String number) {
		if (depth == k) {
			result.add(number);
			return;
		}

		for (int i = 0; i < 10; i++) {
			if (used[i]) {
				continue;
			}

			char sign = signs[depth];
			int lastDigit = number.charAt(number.length() - 1) - '0';

			if ((sign == '<' && lastDigit < i) || (sign == '>' && lastDigit > i)) {
				used[i] = true;
				dfs(depth + 1, number + i);
				used[i] = false;
			}
		}
	}
}
