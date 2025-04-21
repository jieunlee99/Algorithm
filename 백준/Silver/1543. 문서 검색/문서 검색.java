
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String doc = br.readLine();
		String find = br.readLine();

		int lenDoc = doc.length();
		int lenFind = find.length();

		doc = doc.replace(find, "");

		System.out.println((lenDoc - doc.length()) / lenFind);
	}

}