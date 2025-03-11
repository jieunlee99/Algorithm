import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[] isCheck = new boolean[26];

		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < n; t++) {
			String option = br.readLine();
			String str = findRepresentativeKey(option, isCheck);
			sb.append(str).append("\n");
		}

		System.out.println(sb);
	}

	static String findRepresentativeKey(String option, boolean[] isCheck) {
		// 1. 단어의 첫 글자로 단축키 배정 시도
		String[] words = option.split(" ");
		for (int wordIdx = 0; wordIdx < words.length; wordIdx++) {
			if (words[wordIdx].isEmpty())
				continue; // 빈 문자열 방지

			char alphabet = Character.toLowerCase(words[wordIdx].charAt(0));
			int num = alphabet - 'a';

			if (!isCheck[num]) {
				isCheck[num] = true;
				return makeKey(words, wordIdx);
			}
		}

		// 2. 문장 전체 탐색 (i = 0부터 시작해야 함!)
		for (int i = 0; i < option.length(); i++) { // i = 0부터 탐색
			if (option.charAt(i) == ' ')
				continue;

			char alphabet = Character.toLowerCase(option.charAt(i));
			int num = alphabet - 'a';

			if (!isCheck[num]) {
				isCheck[num] = true;
				return makeKey(option, i);
			}
		}

		// 3. 단축키를 지정할 수 없을 경우 원본 반환
		return option;
	}

	// wordIdx번째 단어의 첫 글자가 단축키로 지정된 경우
	static String makeKey(String[] words, int wordIdx) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			if (i == wordIdx) {
				sb.append("[").append(words[i].charAt(0)).append("]").append(words[i].substring(1));
			} else {
				sb.append(words[i]);
			}
			if (i < words.length - 1)
				sb.append(" "); // 마지막 단어 뒤에는 공백 추가 X
		}
		return sb.toString();
	}

	// option의 idx번째 글자가 단축키로 지정된 경우
	static String makeKey(String option, int idx) {
        return option.substring(0, idx) + "[" + option.charAt(idx) + "]" + option.substring(idx + 1);
    }
}
