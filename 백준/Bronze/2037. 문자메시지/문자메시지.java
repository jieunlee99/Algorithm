
import java.io.*;
import java.util.*;

public class Main {

	static int[] number = { 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9 };
	static int[] order = { 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		char[] arr = br.readLine().toCharArray();

		int result = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ' ') {
				result += p;
				continue;
			}

			result += p * order[arr[i] - 'A'];

			if (i > 0 && arr[i - 1] != ' ' && number[arr[i - 1] - 'A'] == number[arr[i] - 'A']) {
				result += w;
			}
		}

		System.out.println(result);
	}

}