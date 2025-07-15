import java.util.Scanner;
 
public class Main {
    public static void main(String args[]) {
 
        Scanner sc = new Scanner(System.in);
        
        int wc = sc.nextInt();
        int hc = sc.nextInt();
        
        int ws = sc.nextInt();
        int hs = sc.nextInt();
        
        if ( ws <= wc - 2 && hs <= hc - 2) {
            System.out.print(1);
        }
        else {
            System.out.print(0);
        }
        
    }
}