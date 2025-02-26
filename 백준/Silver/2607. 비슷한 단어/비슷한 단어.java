import java.io.*;
import java.util.*;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		String first = br.readLine();
		int firstLen = first.length();

		// 첫 번째 입력에서 쓰인 알파벳 개수를 센다.
		int[] alpha = new int[26];
		for (int i = 0; i < firstLen; i++) {
			alpha[first.charAt(i) - 'A']++;
		}

		int answer = 0;

		// N-1번 수행
		while (N-- > 1 ) {
			// 기준되는 구성을 복사해온다.
			int[] tmp = alpha.clone();

			String word = br.readLine();
			int wordLen = word.length();

			// 입력된 알파벳의 개수
			int cnt = 0;

			for (int i = 0; i < wordLen; i++) {
				if (tmp[word.charAt(i) - 'A'] > 0) {
					cnt++;
					tmp[word.charAt(i) - 'A']--;
				}
			}

			// 길이가 한 글자 작을 때
			if (firstLen - 1 == wordLen && cnt == wordLen) {
				answer++;
			}

			// 길이가 같을 때
			else if (firstLen == wordLen) {
				if (cnt == firstLen || cnt == wordLen - 1) {
					answer++;
				}
			}

			// 길이가 한 글자 클 때
			else if (firstLen + 1 == wordLen) {
				if (cnt == firstLen) {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}
}
