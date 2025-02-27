import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		
		int cnt0 = 0;
		int cnt1 = 0;

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '0') {
				cnt0++;
			} else {
				cnt1++;
			}
		}

		cnt0 /= 2;
		cnt1 /= 2;

		int i = 0;
		while (cnt1 != 0) {
			if (input.charAt(i) == '1') {
				input = input.substring(0, i) + input.substring(i + 1);
				cnt1--;
				i = -1; // i번 인덱스를 제거할 경우, 다시 시작해야 함
			}
			i++;
		}

		int j = input.length() - 1;
		while (cnt0 != 0) {
			if (input.charAt(j) == '0') {
				input = input.substring(0, j) + input.substring(j + 1);
				cnt0--;
				j = input.length();
			}
			j--;
		}

		System.out.println(input);
	}
}