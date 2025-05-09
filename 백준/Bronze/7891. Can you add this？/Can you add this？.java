import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();

        for (int i = 0; i < number; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println(x+y);
        }
    }
}