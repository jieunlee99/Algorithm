import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] days = { "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN" };
		int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		int n = 0;
		for (int i = 0; i < x; i++) {
			n += months[i];
		}

		n += y - 1;
		System.out.println(days[n % 7]);
	}

}
