import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			boolean hasMack = false;
			boolean hasZack = false;
			
			int[] arr = new int[10];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				arr[j]= Integer.parseInt(st.nextToken());
				sb.append(arr[j]).append(" ");
				if (arr[j] == 18) {
					hasMack = true;
				} else if (arr[j] == 17) {
					hasZack = true;
				}
			}
			sb.append("\n");
			
			if (hasMack && hasZack) {
				sb.append("both").append("\n");
			} else if (hasMack) {
				sb.append("mack").append("\n");
			} else if (hasZack) {
				sb.append("zack").append("\n");
			} else {
				sb.append("none").append("\n");
			}
			sb.append("\n");
		}

		System.out.print(sb.toString());
	}

}
