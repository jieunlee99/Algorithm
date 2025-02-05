import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int temp = 1;
		int cnt = 1;

		while (true) {
			if (temp >= N) {
				break;
			}
			temp += 6 * cnt;
			cnt++;
		}

		System.out.println(cnt);
	}

}
