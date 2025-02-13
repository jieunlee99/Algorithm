import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String input = br.readLine();
			if (input.equals("end")) {
				break;
			}

			char[] arr = input.toCharArray();

			boolean containsVowels = false; // 모음 포함 검사
			boolean threeTimesInARow = false; // 모음 or 자음 세 번 연속인지 검사
			int vowelsInARowCount = 0; // 연속으로 나온 모음 횟수
			int consonantInARowCount = 0; // 연속으로 나온 자음 횟수
			boolean doubleInARowCheck = false; // 같은 글자가 연속으로 두 번 이상 나오는지 검사

			for (int i = 0; i < arr.length; i++) {
				if (isVowel(arr[i])) {
					containsVowels = true;
					vowelsInARowCount++;
					consonantInARowCount = 0;
				} else {
					consonantInARowCount++;
					vowelsInARowCount = 0;
				}

				if (vowelsInARowCount >= 3 || consonantInARowCount >= 3) {
					threeTimesInARow = true;
					break;
				}

				if (i >= 1) {
					if (arr[i - 1] == arr[i]) {
						if (arr[i] == 'e') {
							continue;
						} else if (arr[i] == 'o') {
							continue;
						}
						doubleInARowCheck = true;
						break;
					}
				}
			}

			sb.append("<");
			for (int j = 0; j < arr.length; j++) {
				sb.append(arr[j]);
			}
			sb.append("> is ");
			if (!containsVowels || threeTimesInARow || doubleInARowCheck) {
				sb.append("not ");
			}
			sb.append("acceptable.\n");
		}

		System.out.println(sb);
	}

	static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
