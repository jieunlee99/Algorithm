import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = 1;
		while (true) {

			int n0 = Integer.parseInt(br.readLine());
			if (n0 == 0) {
				break;
			}

			int n1 = 3 * n0;

			int n2;
			if (n1 % 2 == 0) {
				n2 = n1 / 2;
			} else {
				n2 = (n1 + 1) / 2;
			}

			int n3 = 3 * n2;

			int n4 = n3 / 9;

			if (n1 % 2 == 0) {
				n0 = 2 * n4;
			} else {
				n0 = 2 * n4 + 1;
			}

			sb.append(t++).append(". ").append(n1 % 2 == 0 ? "even" : "odd").append(" ").append(n4).append("\n");
		}

		System.out.println(sb);
	}

}
