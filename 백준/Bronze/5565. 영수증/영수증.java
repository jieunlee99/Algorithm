import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int totalSum = Integer.parseInt(br.readLine());

		int sum = 0;

		for (int i = 0; i < 9; i++) {
			sum += Integer.parseInt(br.readLine());
		}

		System.out.println(totalSum-sum);
	}

}
