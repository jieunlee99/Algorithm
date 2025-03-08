import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int H = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		System.out.println(H * 60 + M);
	}

}
