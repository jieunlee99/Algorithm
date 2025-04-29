import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine();

		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char t = str.charAt(i);
			if (t == 'a' || t == 'e' || t == 'i' || t == 'o' || t == 'u')
				++count;
		}
		System.out.println(count);
		sc.close();
	}
}