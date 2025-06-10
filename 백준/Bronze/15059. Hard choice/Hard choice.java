import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int c1 = Integer.parseInt(st.nextToken());
		int b1 = Integer.parseInt(st.nextToken());
		int p1 = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int c2 = Integer.parseInt(st.nextToken());
		int b2 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());

		int result = Math.max(0, c2 - c1) + Math.max(0, b2 - b1) + Math.max(0, p2 - p1);
		System.out.println(result);
	}

}
