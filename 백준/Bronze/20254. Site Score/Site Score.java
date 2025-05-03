import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int ur = scanner.nextInt();
        int tr = scanner.nextInt();
        int uo = scanner.nextInt();
        int to = scanner.nextInt();
        
        System.out.println((56 * ur) + (24 * tr) + (14 * uo) + (6 * to));
    }
}