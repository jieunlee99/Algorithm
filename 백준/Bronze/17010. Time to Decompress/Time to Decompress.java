import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			int k = sc.nextInt();
			String a = sc.next();
			for(int j = 0; j < k; j++) {
				System.out.print(a);
			}
			System.out.println();
		}
		sc.close();
	}
}
