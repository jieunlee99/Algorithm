import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int burger = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			burger = Math.min(Integer.parseInt(br.readLine()), burger);
		}

		int drink = Integer.MAX_VALUE;
		for (int i = 0; i < 2; i++) {
			drink = Math.min(Integer.parseInt(br.readLine()), drink);
		}

		System.out.println(burger + drink -50);
	}

}
