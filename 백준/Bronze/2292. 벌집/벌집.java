import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int i = 1;
		while (N > 0) {
			if (N == 1)
				break;
			N -= i * 6;
			i++;
		}

		System.out.println(i);
	}
}

