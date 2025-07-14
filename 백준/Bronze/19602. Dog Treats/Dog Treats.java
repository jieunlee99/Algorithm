import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();
        int M = sc.nextInt();
        int L = sc.nextInt();

        int result = S + (2 * M) + (3 * L);
        System.out.println(result > 9 ? "happy" : "sad");
    }
}