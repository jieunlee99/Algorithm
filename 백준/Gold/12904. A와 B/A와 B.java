import java.io.*;
import java.util.*;

public class Main {

	static String S, T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = br.readLine();
		T = br.readLine();

		while (T.length() > S.length()) {
			// 마지막이 A이면 A 제거
			// 마지막이 B이면 B 제거 후 뒤집기
			if (T.charAt(T.length() - 1) == 'A') {
				T = T.substring(0, T.length() - 1);
			} else if (T.charAt(T.length() - 1) == 'B') {
				T = T.substring(0, T.length() - 1);
				T = new StringBuilder(T).reverse().toString();
			}
		}

		System.out.println(S.equals(T) ? 1 : 0);
	}

}
