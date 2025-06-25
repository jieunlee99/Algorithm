import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for(int i = 0; i < t; i++) {
			int n = sc.nextInt();
			for(int j = 0; j < n; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				System.out.println(a + b + " " + a * b);
			}
		}
		sc.close();
	}
}